package com.example.kotlin_coroutine

import android.app.Application
import com.example.kotlin_coroutine.exception.GlobalThreadUncaughtExceptionHandler
import timber.log.Timber

lateinit var appContext: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        Timber.plant(Timber.DebugTree())

        GlobalThreadUncaughtExceptionHandler.setUp()

    }
}