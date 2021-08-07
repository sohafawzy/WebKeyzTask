package com.soha.webkeyztask.data.remote

import io.reactivex.rxjava3.core.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET("{path}")
    fun getApi(@Path(value = "path",encoded = true) path: String,@QueryMap params:HashMap<String, Any>): Flowable<ResponseBody?>
}