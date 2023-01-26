package com.hwaryun.navigation

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object FragmentConnector : KoinComponent {

    val Profile: ProfileFragmentConnector by inject()
}