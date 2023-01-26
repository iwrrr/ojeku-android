package com.hwaryun.customer.search

import com.hwaryun.locationapi.LocationWebServices
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CustomerSearchModule {

    fun modules() = module {
        single { LocationWebServices.build() }
        viewModel { SearchLocationViewModel(get()) }
    }
}