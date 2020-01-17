package com.example.androiddemo.architecture_demo.mvp.presenter

interface LoginPresenter {
    fun validateCredentials(username: String, password: String)

    fun onDestory()
}