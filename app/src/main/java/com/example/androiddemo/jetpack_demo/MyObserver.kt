package com.example.androiddemo.jetpack_demo

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.androiddemo.jetpack_demo.`interface`.MyObserverInterface

/**
 * Activity 或者 Fragment 的 Lifecycle 的观察者
 */
class MyObserver: LifecycleObserver {

    companion object {
        const val TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observerOnStart() {
        Log.d(TAG, "observerOnStart(): --------- onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun observerOnResume() {
        Log.d(TAG, "observerOnResume(): --------- onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun observerOnStop() {
        Log.d(TAG, "observerOnStop(): --------- onStop")
    }



}