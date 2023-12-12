package com.D121211097.taskapp

import android.app.Application
import com.D121211097.taskapp.data.AppContainer
import com.D121211097.taskapp.data.DefaultAppContainer


class MyApplication: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}