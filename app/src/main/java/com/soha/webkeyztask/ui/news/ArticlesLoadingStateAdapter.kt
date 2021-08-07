package com.soha.webkeyztask.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soha.webkeyztask.databinding.ItemNetworkStateBinding

class ArticlesLoadingStateAdapter (private val retry: () -> Unit) :
    LoadStateAdapter<ArticlesLoadingStateAdapter.ArticleLoadStateViewHolder>() {

    inner class ArticleLoadStateViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsgItem.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retyBtn.isVisible= loadState is LoadState.Error
            binding.errorMsgItem.isVisible = loadState is LoadState.Error
            binding.retyBtn.setOnClickListener {
                retry()
            }
        }
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
    }

    override fun onBindViewHolder(holder: ArticleLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = ArticleLoadStateViewHolder(
        ItemNetworkStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}