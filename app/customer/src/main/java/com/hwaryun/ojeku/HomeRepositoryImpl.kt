package com.hwaryun.ojeku

import android.location.Location
import com.hwaryun.core.LocationManager
import com.hwaryun.core.extensions.default
import com.hwaryun.core.extensions.mapStateEvent
import com.hwaryun.core.state.StateEventManager

class HomeRepositoryImpl(
    private val locationManager: LocationManager
) : HomeRepository {

    private val _locationResult = default<Location>()
    override val locationResult: StateEventManager<Location>
        get() = _locationResult

    override suspend fun getLocation() {
        locationManager.getLocationFlow()
            .mapStateEvent()
            .collect {
                _locationResult.emit(it)
            }
    }
}