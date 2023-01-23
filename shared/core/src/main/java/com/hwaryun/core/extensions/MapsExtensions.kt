package com.hwaryun.core.extensions

import android.animation.ValueAnimator
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

fun Location.toLatLng(): LatLng {
    return LatLng(latitude, longitude)
}

fun LatLng.toLocation(): Location {
    val location = Location("").also {
        it.latitude = latitude
        it.longitude = longitude
    }
    return location
}

fun Marker.moveSmoothly(newLatLng: LatLng) {
    val animator = ValueAnimator.ofFloat(0f, 100f)

    val deltaLatitude = doubleArrayOf(newLatLng.latitude - this.position.latitude)
    val deltaLongitude = newLatLng.longitude - this.position.longitude
    val prevStep = floatArrayOf(0f)

    animator.duration = 1000
    animator.addUpdateListener { animation ->
        val currentValue = animation.animatedValue as Float
        val deltaStep = (currentValue - prevStep[0]).toDouble()
        prevStep[0] = currentValue

        val updatedLatitude = this.position.latitude + deltaLatitude[0] * deltaStep * 1.0 / 100
        val updatedLongitude = this.position.longitude + deltaLongitude * deltaStep * 1.0 / 100
        val updatedLatLng = LatLng(updatedLatitude, updatedLongitude)

        this.position = updatedLatLng
    }
    animator.start()
}