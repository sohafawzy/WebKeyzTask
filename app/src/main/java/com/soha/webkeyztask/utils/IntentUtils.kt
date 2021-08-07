package com.soha.webkeyztask.utils

import android.content.Intent
import android.net.Uri

object IntentUtils {

    fun sendTextIntent(text:String):Intent{
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    fun browseIntent(url :String?):Intent{
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        return intent
    }
}