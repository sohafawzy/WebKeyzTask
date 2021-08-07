package com.soha.webkeyztask.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let { Glide.with(view.context).load(it).into(view)}
}

@BindingAdapter("date")
fun setDate(view:TextView,date:String?){
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.US)
    val output = SimpleDateFormat("dd MMM,yyyy HH:mm",Locale.US)

    var d: Date? = null
    try {
        d = input.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    d?.let {
        view.text = output.format(it)
    }
}