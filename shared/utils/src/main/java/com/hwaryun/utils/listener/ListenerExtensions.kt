package com.hwaryun.utils.listener

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

@Suppress("UNCHECKED_CAST")
fun <T : ActivityListener> Fragment.findActivityListener(): T? {
    return activity as? T
}

@Suppress("UNCHECKED_CAST")
fun <T : FragmentListener> FragmentActivity.findFragmentListener(tag: String): T? {
    return supportFragmentManager.findFragmentByTag(tag) as? T
}