package com.example.kotlin_coroutine.exception

import timber.log.Timber
import java.lang.Thread.UncaughtExceptionHandler

class GlobalThreadUncaughtExceptionHandler : UncaughtExceptionHandler {

    companion object {
        fun setUp() {
            Thread.setDefaultUncaughtExceptionHandler(GlobalThreadUncaughtExceptionHandler())
        }
    }

    private val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(t: Thread, e: Throwable) {
        Timber.e(e, "Uncaught exception in thread: ${t.name}")
        defaultUncaughtExceptionHandler?.uncaughtException(t, e)
    }

}