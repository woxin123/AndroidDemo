package online.mengchen.androiddemo.architecture_demo.mvp.model

import online.mengchen.androiddemo.architecture_demo.mvp.listener.OnLoginFinishedListener

interface LoginModel {
    fun login(username: String, password: String, listener: OnLoginFinishedListener)
}