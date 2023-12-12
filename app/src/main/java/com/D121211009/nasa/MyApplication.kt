package com.D121211009.nasa

import android.app.Application
import com.D121211009.nasa.data.AppContainer
import com.D121211009.nasa.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}