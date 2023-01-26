package com.hwaryun.ojeku

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.hwaryun.core.extensions.toLatLng
import com.hwaryun.core.state.StateEventSubscriber
import com.hwaryun.ojeku.databinding.FragmentHomeBinding
import com.hwaryun.utils.BindingFragment
import com.hwaryun.utils.listener.findActivityListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class HomeFragment : BindingFragment<FragmentHomeBinding>(), HomeFragmentListener {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var map: GoogleMap

    override fun inflateBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
            getLocationWithPermission()
        }

        viewModel.subscribeLocation(locationSubscriber())
    }

    override fun onMessageFromActivity(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun locationSubscriber() = object : StateEventSubscriber<Location> {
        override fun onIdle() {
            println("------- location idle")
        }

        override fun onLoading() {
            println("------- location loading")
        }

        override fun onEmpty() {
            //
        }

        override fun onFailure(throwable: Throwable) {
            println("------- location failure -> ${throwable.message}")
        }

        override fun onSuccess(data: Location) {
            println("------- location success -> $data")

            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(data.toLatLng(), 20f)
            map.animateCamera(cameraUpdate)

            findActivityListener<MainActivityListener>()?.onLocationResult(data)
        }
    }

    @AfterPermissionGranted(value = RC_LOCATION)
    private fun getLocationWithPermission() {
        val fineLocation = Manifest.permission.ACCESS_FINE_LOCATION
        val coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION
        context?.let {
            if (EasyPermissions.hasPermissions(it, fineLocation, coarseLocation)) {
                viewModel.getLocation()
            } else {
                EasyPermissions.requestPermissions(
                    this,
                    "Granted for location",
                    RC_LOCATION,
                    fineLocation,
                    coarseLocation
                )
            }
        }
    }

    companion object {
        private const val RC_LOCATION = 15
    }
}