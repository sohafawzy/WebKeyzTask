package com.soha.webkeyztask.utils

import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

object NetworkUtils {
    fun Throwable.getNetworkError(): String {
        return when {
            this is UnknownHostException -> "No Internet Connection"
            this is HttpException && this.code() == 500 -> "Server Error"
            this is HttpException && this.code() == 404 -> "Not found"
            this is HttpException && this.code() == 401 -> "Unauthorized"
            this is HttpException && this.code() == 0 -> this.cause.toString()
            this is HttpException && this.code() != 0 -> JSONObject(this.response()?.errorBody()?.string()).optString("error") ?: "Unexpected error"
            else -> this.message ?: "Unexpected error"
        }
    }
}