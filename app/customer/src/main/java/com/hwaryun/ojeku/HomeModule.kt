package com.hwaryun.ojeku

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {

    fun module() = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
        viewModel { HomeViewModel(get()) }
    }
}