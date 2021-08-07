package com.soha.webkeyztask.data

import io.reactivex.rxjava3.core.Single

interface DataSource {
    fun makeGetRequest(path: String,params:HashMap<String,Any>) : Single<String>?
}