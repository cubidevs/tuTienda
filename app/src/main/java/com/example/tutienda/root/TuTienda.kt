package com.example.tutienda.root

import android.app.Application
import android.content.Context

class TuTienda:Application() {

    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
    }
    fun getAppContext(): Context {
        return appContext!!
    }
}