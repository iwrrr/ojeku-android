package com.hwaryun.koin

import android.content.Context
import com.hwaryun.auth.AuthModule
import com.hwaryun.core.CoreModule
import com.hwaryun.locationapi.LocationApiModule
import com.hwaryun.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinStarter {

    fun onCreate(context: Context, featureModule: List<Module> = emptyList()) {
        val modules = listOf(
            AuthModule.modules(),
            NetworkModule.modules(),
            CoreModule.modules(),
            LocationApiModule.modules(),
        ) + featureModule
        startKoin {
            androidContext(context)
            modules(modules)
        }
    }
}