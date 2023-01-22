package com.hwaryun.core

import android.content.Context
import com.hwaryun.auth.AuthModule
import com.hwaryun.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinStarter {

    fun onCreate(context: Context) {
        val modules = listOf(
            NetworkModule.modules(),
            AuthModule.modules(),
        )
        startKoin {
            androidContext(context)
            modules(modules)
        }
    }
}