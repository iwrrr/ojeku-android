package com.hwaryun.auth

import com.hwaryun.network.RetrofitBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthWebServices {

    @POST(Endpoint.LOGIN)
    suspend fun login(
        @Body request: Any
    ): Response<Any>

    @POST(Endpoint.REGISTER_DRIVER)
    suspend fun registerDriver(
        @Body request: Any
    ): Response<Any>

    @POST(Endpoint.REGISTER_CUSTOMER)
    suspend fun registerCustomer(
        @Body request: Any
    ): Response<Any>

    companion object : KoinComponent {
        private val retrofitBuilder: RetrofitBuilder by inject()
        fun build(): AuthWebServices {
            return retrofitBuilder.build().create(AuthWebServices::class.java)
        }
    }

    object Endpoint {
        private const val PREFIX = "/api/v1/user"
        const val LOGIN = "$PREFIX/login"
        const val REGISTER_DRIVER = "$PREFIX/driver/register"
        const val REGISTER_CUSTOMER = "$PREFIX/customer/register"
    }
}