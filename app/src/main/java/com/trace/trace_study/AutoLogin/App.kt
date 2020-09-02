package com.trace.trace_study.AutoLogin

import android.app.Application

class App: Application() {
    override fun onCreate(){
        super.onCreate()
        MySharedPreferences.init(this)

    }
}