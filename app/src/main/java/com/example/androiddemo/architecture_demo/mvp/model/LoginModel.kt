package com.example.androiddemo.architecture_demo.mvp.model

import android.os.MessageQueue
import com.example.androiddemo.architecture_demo.mvp.listener.OnLoginFinishedListener

interface LoginModel {
    fun login(username: String, password: String, listener: OnLoginFinishedListener)
}