package com.sanogueralorenzo.presentation

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

import java.util.concurrent.atomic.AtomicBoolean

sealed class DataState {
    object LOADING : DataState()
    object SUCCESS : DataState()
    object ERROR : DataState()
}

data class Data<out T> constructor(val dataState: DataState, val data: T? = null, val message: String? = null)

fun <T> MutableLiveData<Data<T>>.setSuccess(data: T? = null) =
        postValue(Data(DataState.SUCCESS, data))

fun <T> MutableLiveData<Data<T>>.setLoading() =
        postValue(Data(DataState.LOADING, value?.data))

fun <T> MutableLiveData<Data<T>>.setError(message: String? = null) =
        postValue(Data(DataState.ERROR, value?.data, message
                ?: "Something went wrong"))

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        if (hasActiveObservers()) {
            throw RuntimeException("Multiple observers registered, SingleLiveEvent is intended for a unique observer")
        }

        super.observe(owner, Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }
}