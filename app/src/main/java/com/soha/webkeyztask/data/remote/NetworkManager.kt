package com.soha.webkeyztask.data.remote

import com.soha.webkeyztask.bases.BaseApplication
import com.soha.webkeyztask.data.DataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.kodein.di.DIAware
import org.kodein.di.instance

class NetworkManager (baseApplication: BaseApplication): DataSource, DIAware {
    override val di by baseApplication.di
    val apiService :ApiService by instance()

    override fun makeGetRequest(
        path: String,
        params: HashMap<String, Any>
    ): Single<String>? {
        return Single.create { singleEmitter->
            apiService.getApi(path,params)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe ({
                    singleEmitter.onSuccess(it?.string())
                },{
                    singleEmitter.onError(it)
                })
        }
    }
}