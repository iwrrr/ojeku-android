package com.hwaryun.ojeku

import com.hwaryun.utils.listener.FragmentListener

interface HomeFragmentListener : FragmentListener {
    fun onMessageFromActivity(message: String)
}