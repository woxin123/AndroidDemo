package com.example.androiddemo.network_demo

import android.util.Log
import org.apache.http.HttpVersion
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.params.BasicHttpParams
import org.apache.http.params.HttpConnectionParams
import org.apache.http.params.HttpParams
import org.apache.http.params.HttpProtocolParams
import org.apache.http.protocol.HTTP
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpURLConnectionDemo {
    private fun getHttpURLConnection(url: String): HttpURLConnection? {
        var mHttpUrlConnection: HttpURLConnection? = null
        try {
            val mUrl = URL(url)
            mHttpUrlConnection = mUrl.openConnection() as HttpURLConnection
            // 设置链接超时时间
            mHttpUrlConnection.connectTimeout = 15000
            // 设置读取超时时间
            mHttpUrlConnection.readTimeout = 15000
            // 添加 Header
            mHttpUrlConnection.requestMethod = "GET"
            // 添加 Handler
            mHttpUrlConnection.setRequestProperty("connection", "Keep-Alive")
            // 接收输入流
            mHttpUrlConnection.doInput = true
            // 传递参数时需要开启
//            mHttpUrlConnection.doOutput = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return mHttpUrlConnection
    }

    fun useHttpURLConnectionGet(url: String): String? {
        var mInputStream: InputStream? = null
        val mHttpURLConnection: HttpURLConnection = getHttpURLConnection(url)!!
        try {
            mHttpURLConnection.connect()
            Log.d(javaClass.simpleName.toUpperCase(), mHttpURLConnection.responseCode.toString())
            mInputStream = mHttpURLConnection.inputStream
            val code = mHttpURLConnection.responseCode

            return "请求状态码: $code\n请求结果:\n${convertStreamToString(mInputStream)}"
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            mInputStream?.close()
        }
        return null
    }
}