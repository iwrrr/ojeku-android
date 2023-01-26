package com.hwaryun.navigation

import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.reflect.KClass

fun <T : Fragment> FragmentManager.attachFragment(
    frameLayout: FrameLayout,
    kClass: KClass<T>,
    backStackName: String? = null
): String {
    val instance = kClass.java.newInstance()
    val tag = kClass.qualifiedName.orEmpty()
    val newBackStackName = backStackName ?: tag
    this.beginTransaction()
        .add(frameLayout.id, instance, tag)
        .addToBackStack(newBackStackName)
        .commit()

    return tag
}

fun <T : Fragment> FragmentManager.replaceFragment(
    frameLayout: FrameLayout,
    kClass: KClass<T>,
    backStackName: String? = null
): String {
    val instance = kClass.java.newInstance()
    val tag = kClass.qualifiedName.orEmpty()
    val newBackStackName = backStackName ?: tag
    this.beginTransaction()
        .replace(frameLayout.id, instance, tag)
        .addToBackStack(newBackStackName)
        .commit()

    return tag
}