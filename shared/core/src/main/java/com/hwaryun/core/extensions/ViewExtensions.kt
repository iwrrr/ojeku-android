package com.hwaryun.core.extensions

import android.view.View
import androidx.annotation.IdRes

fun <T : View> View.findIdByLazy(@IdRes id: Int): Lazy<T> {
    return lazy { findViewById(id) }
}