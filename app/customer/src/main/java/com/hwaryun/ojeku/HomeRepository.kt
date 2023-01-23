package com.hwaryun.ojeku

import android.location.Location
import com.hwaryun.core.state.StateEventManager

interface HomeRepository {
    val locationResult: StateEventManager<Location>

    suspend fun getLocation()
}