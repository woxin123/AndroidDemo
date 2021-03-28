package com.example.kotlin_coroutine.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import timber.log.Timber

val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor(object : Logger {
    override fun log(message: String) {
        Timber.d(message)
    }

}).setLevel(Level.BASIC)).build()