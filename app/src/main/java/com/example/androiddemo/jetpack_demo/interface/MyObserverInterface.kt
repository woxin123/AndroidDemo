package com.example.androiddemo.jetpack_demo.`interface`

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

interface MyObserverInterface {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart() {
        println("onCreate")
    }

}