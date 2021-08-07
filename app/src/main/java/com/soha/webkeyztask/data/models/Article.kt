package com.soha.webkeyztask.data.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Article(val author:String?, val title:String?, val description:String?, val url:String?, val urlToImage:String?,val publishedAt:String?,val source:Source?):Serializable
