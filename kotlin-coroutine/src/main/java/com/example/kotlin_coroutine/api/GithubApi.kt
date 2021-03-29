package com.example.kotlin_coroutine.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import timber.log.Timber

data class GitUser(val id: String, val name: String, val url: String)

val gitHubServiceApi by lazy {
    val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request()).apply {
                Timber.d("request: ${code}")
            }
        }.build())
        .baseUrl("https://api.github.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    retrofit.create(GitHubServiceApi::class.java)
}

interface GitHubServiceApi {

    @GET("users/{login}")
    fun getUserCallback(@Path("login") login: String): Call<GitUser>

    @GET("users/{login}")
    fun getUserSuspend(@Path("login") login: String): GitUser
}