package com.soha.webkeyztask.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.si.reach.bases.BaseViewModel
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.data.remote.ArticlesDataSource
import com.soha.webkeyztask.data.repositories.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class NewsViewModel(val repository: NewsRepository): BaseViewModel() {

    val data = MutableLiveData<PagingData<Article>>()
    @ExperimentalCoroutinesApi
    var listData = Pager(PagingConfig(pageSize = 20,enablePlaceholders = true)) {
        ArticlesDataSource(repository)
    }.flowable.cachedIn(viewModelScope)


    fun fetchArticles() {
        launch {
            listData.subscribe{
                data.value = it
            }
        }
    }
}