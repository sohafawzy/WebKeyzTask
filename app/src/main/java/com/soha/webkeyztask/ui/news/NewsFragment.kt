package com.soha.webkeyztask.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.soha.webkeyztask.R
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.data.repositories.NewsRepository
import com.soha.webkeyztask.databinding.FragmentNewsBinding
import com.soha.webkeyztask.ui.details.ArticleDetailsFragment
import com.soha.webkeyztask.utils.FragmentNavigation
import com.soha.webkeyztask.utils.RecyclerItemClickListener
import com.soha.webkeyztask.utils.createFactory

class NewsFragment : Fragment(),RecyclerItemClickListener{

    private lateinit var binding : FragmentNewsBinding
    private val viewModel by viewModels<NewsViewModel> { createFactory(NewsRepository::class.java)}
    private val articlesAdapter by lazy { ArticlesAdapter(this) }
    lateinit var fragmentNavigation: FragmentNavigation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news,container,false)

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter =articlesAdapter.withLoadStateHeaderAndFooter(
                header = ArticlesLoadingStateAdapter(articlesAdapter::retry),
                footer = ArticlesLoadingStateAdapter (articlesAdapter::retry)
            )
        }
        articlesAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(requireContext(),it.error.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        viewModel.data.observe(viewLifecycleOwner,{
            articlesAdapter.submitData(lifecycle,it)
        })
        viewModel.fetchArticles()

        return binding.root
    }


    override fun onArticleClick(article: Article) {
        val fragment = ArticleDetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("article",article)
        fragment.arguments = bundle
        fragmentNavigation.pushFragment(fragment)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            fragmentNavigation = context
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.app_name)
        }
    }
}