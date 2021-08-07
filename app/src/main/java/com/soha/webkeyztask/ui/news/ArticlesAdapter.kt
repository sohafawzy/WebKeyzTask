package com.soha.webkeyztask.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.databinding.ItemArticleBinding
import com.soha.webkeyztask.utils.RecyclerItemClickListener

class ArticlesAdapter(val listener: RecyclerItemClickListener) : PagingDataAdapter<Article , ArticleViewHolder>(
    DiffUtilCategoryItem()
)  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return  ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let {  article->
            holder.bind(article)
            holder.itemView.setOnClickListener { listener.onArticleClick(article) }
        }
    }

    private class DiffUtilCategoryItem : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem.url == newItem.url
    }

}