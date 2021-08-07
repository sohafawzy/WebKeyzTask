package com.soha.webkeyztask.data.repositories

import com.soha.webkeyztask.data.DataSource
import com.soha.webkeyztask.data.remote.NewsResponse
import com.soha.webkeyztask.utils.Constants
import com.soha.webkeyztask.utils.JsonConverter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsRepository(private val dataSource: DataSource) : Repository(dataSource) {

    fun getNews(page: Int): Single<NewsResponse> {
        val queries: HashMap<String, Any> = hashMapOf()
        queries[Constants.apiKeyQuery] = Constants.API_KEY
        queries[Constants.countryQuery] = "us"
        queries [Constants.pageQuery] = page
        return Single.create { emitter ->
            dataSource.makeGetRequest(Constants.topHeadlinesEndPoint, queries)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(Schedulers.io())
                ?.subscribe({ data ->
                    emitter.onSuccess(
                        JsonConverter.convertJsonToObj(
                            data,
                            NewsResponse::class.java
                        )
                    )
                }, { throwable ->
                    emitter.onError(throwable)
                })
        }
    }
}