package com.example.tutienda.root

import android.app.Application
import android.content.Context

class TuTienda : Application() {
    private var sContext: Context? = null
    private var sInstance: TuTienda? = null
    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
    }

    fun getContext(): Context? {
        return sContext
    }

    fun getInstance(): TuTienda {
        return sInstance!!
    }

    fun setAppContext(context: Context) {
        sContext = context
    }

}
