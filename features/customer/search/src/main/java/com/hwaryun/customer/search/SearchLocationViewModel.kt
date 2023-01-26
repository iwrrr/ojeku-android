package com.hwaryun.customer.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.core.extensions.asFlowStateEvent
import com.hwaryun.core.extensions.convertEventToSubscriber
import com.hwaryun.core.state.MutableStateEventManager
import com.hwaryun.core.state.StateEvent
import com.hwaryun.core.state.StateEventManager
import com.hwaryun.core.state.StateEventSubscriber
import com.hwaryun.locationapi.LocationWebServices
import com.hwaryun.locationapi.Mapper
import com.hwaryun.locationapi.StateLocationList
import com.hwaryun.locationapi.entity.LocationData
import kotlinx.coroutines.launch

class SearchLocationViewModel(
    private val webServices: LocationWebServices
) : ViewModel() {

    private val _locationResult: MutableLiveData<StateLocationList> =
        MutableLiveData(StateEvent.Idle())
    val locationResult: LiveData<StateLocationList>
        get() = _locationResult

    private val _locationStateManager: MutableStateEventManager<List<LocationData>> =
        MutableStateEventManager()
    private val locationStateManager: StateEventManager<List<LocationData>>
        get() = _locationStateManager

    fun getLocations(name: String) = locationStateManager.createScope(viewModelScope).launch {
        val coordinateString = "-6.2509613,107.0954131"
        webServices.searchLocation(name, coordinateString).asFlowStateEvent {
            Mapper.mapLocationResponseToData(it)
        }.collect(_locationStateManager)
    }

    fun subscribeLocationManager(subscriber: StateEventSubscriber<List<LocationData>>) {
        convertEventToSubscriber(locationStateManager, subscriber)
    }
}