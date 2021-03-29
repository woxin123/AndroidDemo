package com.example.kotlin_coroutine

import android.app.Application
import com.example.kotlin_coroutine.db.UserDao
import com.example.kotlin_coroutine.exception.GlobalThreadUncaughtExceptionHandler
import com.example.kotlin_coroutine.settings.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

lateinit var appContext: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        Timber.plant(Timber.DebugTree())

        GlobalThreadUncaughtExceptionHandler.setUp()

        if (Settings.firstTimeLaunch) {
            GlobalScope.launch { UserDao.insertSampleData() }
            Settings.firstTimeLaunch = false
        }

    }
}