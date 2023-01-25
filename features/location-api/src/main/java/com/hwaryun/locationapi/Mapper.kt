package com.hwaryun.locationapi

import com.google.android.gms.maps.model.LatLng
import com.hwaryun.locationapi.entity.LocationData
import com.hwaryun.locationapi.response.LocationResponse
import com.hwaryun.utils.orZero

object Mapper {

    fun mapLocationResponseToData(locationResponse: LocationResponse?): List<LocationData> {
        val mapperData: (LocationResponse.Data?) -> LocationData = {
            val name = it?.name.orEmpty()
            val address = "${it?.address?.district}, ${it?.address?.country}, ${it?.address?.city}"
            val latLng =
                LatLng(it?.coordinate?.latitude.orZero(), it?.coordinate?.longitude.orZero())
            LocationData(name, address, latLng)
        }

        return locationResponse?.data?.map(mapperData).orEmpty()
    }
}