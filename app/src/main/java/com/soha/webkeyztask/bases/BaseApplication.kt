package com.soha.webkeyztask.bases

import android.app.Application
import android.content.Context
import com.soha.webkeyztask.di.networkModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

class BaseApplication : Application() ,DIAware {
      override val di = DI.lazy {
        bind<Context>("ApplicationContext") with singleton { this@BaseApplication.applicationContext }
        bind<BaseApplication>() with singleton { this@BaseApplication }
        import(networkModule)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        @get:Synchronized
        lateinit var instance: BaseApplication
    }
}