package com.soha.webkeyztask.ui.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.soha.webkeyztask.R
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.databinding.FragmentArticleDetailsBinding
import com.soha.webkeyztask.utils.IntentUtils.browseIntent
import com.soha.webkeyztask.utils.IntentUtils.sendTextIntent


class ArticleDetailsFragment : Fragment() {
    var  article : Article? = null
    private lateinit var binding : FragmentArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_details,container,false)
        article = arguments?.getSerializable("article")  as Article
        article?.let { binding.article = it }
        setupActionBar()
        binding.btnReadMore.setOnClickListener {
            startActivity(browseIntent(article?.url))
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_article_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuShare->{
                onShareClicked()
            }
        }
        return false
    }

    private fun setupActionBar(){
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = article?.title
        }
    }
    private fun onShareClicked(){
        article?.url?.let {
            startActivity(sendTextIntent(it))
        }
    }
}