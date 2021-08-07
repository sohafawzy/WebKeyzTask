package com.soha.webkeyztask.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.soha.webkeyztask.data.models.Article
import com.soha.webkeyztask.data.repositories.NewsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticlesDataSource(val repository : NewsRepository) : RxPagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {
        val page = params.key ?: 1
        return repository.getNews(page)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it,page) }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(data: NewsResponse, page: Int): LoadResult<Int, Article> {
        return LoadResult.Page(
            data = data.articles,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (data.articles.size < 20) null else page + 1
        )
    }
}