package com.voitenko.dev.kmminviteme.android

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        Core.init { androidContext(this@App) }
    }
}