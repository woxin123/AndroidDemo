package com.example.androiddemo.network_demo

import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpDemo {
    /**
     * 同步请求N
     */
    fun useSyncOkHttp(url: String): String? {
        val okHttpClient = OkHttpClient()
        // 创建请求 Request
        val request = Request.Builder()
            .url(url)
            .build()
        val call = okHttpClient.newCall(request)
        val response = call.execute()
        if (response.isSuccessful) {
            val result = convertStreamToString(response.body!!.byteStream())
            return "请求状态码: ${response.code}\n请求结果: $result"
        } else {
            return null
        }
    }
}