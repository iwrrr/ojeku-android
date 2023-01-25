package com.hwaryun.ojekudriver

import android.app.Application
import com.hwaryun.koin.KoinStarter

class MainDriver : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter.onCreate(this)
    }
}