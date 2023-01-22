package com.hwaryun.auth

import org.koin.dsl.module

object AuthModule {

    fun modules() = module {
        single { AuthWebServicesProvider.provideWebServices() }
    }
}