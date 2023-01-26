package com.hwaryun.customer.profile

import com.hwaryun.navigation.ProfileFragmentConnector
import org.koin.dsl.module

object CustomerProfileModule {

    fun modules() = module {
        single<ProfileFragmentConnector> { ProfileFragmentConnectorProvider() }
    }
}