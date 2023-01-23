package com.hwaryun.core

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CoreModule {

    fun modules() = module {
        single { LocationManager(androidContext()) }
    }
}