package com.hwaryun.locationapi

import com.hwaryun.locationapi.ui.SearchLocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LocationApiModule {

    fun modules() = module {
        single { LocationWebServices.build() }
        viewModel { SearchLocationViewModel(get()) }
    }
}