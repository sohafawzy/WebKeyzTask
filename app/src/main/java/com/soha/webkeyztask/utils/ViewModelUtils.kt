package com.soha.webkeyztask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soha.webkeyztask.bases.BaseApplication
import com.soha.webkeyztask.data.DataSource
import com.soha.webkeyztask.data.remote.NetworkManager
import com.soha.webkeyztask.data.repositories.Repository

fun <V : Repository> createFactory(repoClass: Class<V>): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            val repoConstructor = repoClass.getConstructor(DataSource::class.java)
            val vmConstructor = modelClass.getConstructor(repoClass)
            return vmConstructor.newInstance(repoConstructor.newInstance(NetworkManager(BaseApplication.instance)))
        }
    }
}
