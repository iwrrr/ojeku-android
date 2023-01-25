package com.hwaryun.locationapi

import com.hwaryun.locationapi.response.LocationResponse
import com.hwaryun.network.RetrofitBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationWebServices {

    @GET(Endpoint.SEARCH)
    suspend fun searchLocation(
        @Query(QueryName.SEARCH_NAME) name: String,
        @Query(QueryName.SEARCH_COORDINATE) coordinate: String
    ): Response<LocationResponse>

    object Endpoint {
        internal const val SEARCH = "/api/v1/location/search"
        internal const val REVERSE = "/api/v1/location/reverse"
        internal const val ROUTES = "/api/v1/location/routes"
    }

    object QueryName {
        internal const val SEARCH_NAME = "name"
        internal const val SEARCH_COORDINATE = "coordinate"
    }

    companion object : KoinComponent {
        private val retrofitBuilder: RetrofitBuilder by inject()

        fun build(): LocationWebServices {
            return retrofitBuilder.build().create(LocationWebServices::class.java)
        }
    }
}