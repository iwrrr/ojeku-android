package com.hwaryun.ojeku

import android.app.Application
import com.hwaryun.koin.KoinStarter

class MainCostumer : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter.onCreate(
            context = this,
            featureModule = listOf(
                HomeModule.module()
            )
        )
    }
}