package com.hwaryun.core.state

interface StateEventSubscriber<T> {
    fun onIdle()
    fun onLoading()
    fun onEmpty()
    fun onFailure(throwable: Throwable)
    fun onSuccess(data: T)
}