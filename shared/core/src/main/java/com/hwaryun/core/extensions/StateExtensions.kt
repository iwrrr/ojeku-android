package com.hwaryun.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.core.state.*
import com.hwaryun.core.state.exception.StateApiException
import com.hwaryun.core.state.exception.StateDataEmptyException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

val <T> StateEvent<T>.value: T?
    get() {
        return if (this is StateEvent.Success) {
            this.data
        } else {
            null
        }
    }

fun <T> default() = MutableStateEventManager<T>()

fun <T> Flow<T>.mapStateEvent(): FlowState<T> {
    return catch {
        StateEvent.Failure<T>(it)
    }.map {
        StateEvent.Success(it)
    }
}

fun <T> ViewModel.convertEventToSubscriber(
    eventManager: StateEventManager<T>,
    subscriber: StateEventSubscriber<T>
) {
    eventManager.subscribe(
        scope = viewModelScope,
        onIdle = subscriber::onIdle,
        onLoading = subscriber::onLoading,
        onEmpty = subscriber::onEmpty,
        onFailure = subscriber::onFailure,
        onSuccess = subscriber::onSuccess,
    )
}

fun <T, U> Response<T>.asFlowStateEvent(mapper: (T) -> U): FlowState<U> {
    return flow {
        emit(StateEvent.Loading())
        delay(2000)
        val emitData = try {
            val body = body()
            if (isSuccessful && body != null) {
                val data = mapper.invoke(body)
                if (data is List<*>) {
                    if (data.isEmpty()) {
                        StateEvent.Empty()
                    } else {
                        StateEvent.Success(data as U)
                    }
                } else {
                    StateEvent.Success(data)
                }
            } else {
                val throwable = StateApiException(message(), code())
                StateEvent.Failure(throwable)
            }
        } catch (e: Throwable) {
            StateEvent.Failure(e)
        }

        emit(emitData)
    }
}

fun Throwable.ifStateEmpty(action: (StateDataEmptyException) -> Unit) {
    if (this is StateDataEmptyException) {
        action.invoke(this)
    }
}

fun Throwable.ifNetworkError(action: (StateApiException) -> Unit) {
    if (this is StateApiException) {
        action.invoke(this)
    }
}