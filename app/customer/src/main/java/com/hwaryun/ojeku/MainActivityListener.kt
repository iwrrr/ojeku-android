package com.hwaryun.ojeku

import android.location.Location
import com.hwaryun.utils.listener.ActivityListener

interface MainActivityListener : ActivityListener {
    fun onLocationResult(data: Location)
}