package com.example.androiddemo.activity_demo.moniter_demo

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityMonitor(this)
    }

}