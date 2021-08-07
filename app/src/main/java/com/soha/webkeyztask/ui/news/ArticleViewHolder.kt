package com.soha.webkeyztask.ui.news

import androidx.recyclerview.widget.RecyclerView
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.databinding.ItemArticleBinding

class ArticleViewHolder (private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article : Article){
        binding.article = article
    }
}