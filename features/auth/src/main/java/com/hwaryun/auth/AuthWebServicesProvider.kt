package com.hwaryun.auth

import com.hwaryun.network.RetrofitBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

object AuthWebServicesProvider {

    fun provideWebServices(): AuthWebServices {
        return AuthWebServices.build()
    }
}