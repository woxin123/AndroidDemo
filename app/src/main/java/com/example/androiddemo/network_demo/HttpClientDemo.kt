package com.example.androiddemo.network_demo

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

class HttpClientDemo {
    private fun createHttpClient(): HttpClient {
        val defaultHttpParam: HttpParams = BasicHttpParams()
        // 设置连接超时
        HttpConnectionParams.setConnectionTimeout(defaultHttpParam, 15000)
        // 设置请求超时
        HttpConnectionParams.setSoTimeout(defaultHttpParam, 15000)
        HttpConnectionParams.setTcpNoDelay(defaultHttpParam, true)
        HttpProtocolParams.setVersion(defaultHttpParam, HttpVersion.HTTP_1_1)
        HttpProtocolParams.setContentCharset(defaultHttpParam, HTTP.UTF_8)
        // 持续握手
        HttpProtocolParams.setUseExpectContinue(defaultHttpParam, true)
        return DefaultHttpClient(defaultHttpParam)
    }

    fun useHttpClientGet(url: String): String? {
        val mHttpGet = HttpGet(url)
        mHttpGet.addHeader("Connection", "Keep-Alive")
        var mInputStream: InputStream? = null
        try {
            val mHttpClient = createHttpClient()
            val mHttpResponse = mHttpClient.execute(mHttpGet)
            val mHttpEntity = mHttpResponse.entity
            val code = mHttpResponse.statusLine.statusCode
            if (mHttpEntity != null) {
                mInputStream = mHttpEntity.content
                return "请求状态码: $code\n请求结果:\n${convertStreamToString(mInputStream)}"
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            mInputStream?.close()
        }
        return null
    }



    private fun useHttpClientPost(url: String): String? {
        val mHttpPost = HttpPost(url)
        mHttpPost.addHeader("Connection", "Keep-Alive")
        var mInputStream: InputStream? = null
        try {
            val mHttpClient = createHttpClient()
            val postParam: MutableList<NameValuePair> = mutableListOf()
            postParam.add(BasicNameValuePair("username", "xxx"))
            postParam.add(BasicNameValuePair("password", "xxx"))
            mHttpPost.entity = UrlEncodedFormEntity(postParam)
            val mHttpResponse = mHttpClient.execute(mHttpPost)
            val mHttpEntity = mHttpResponse.entity
            val code = mHttpResponse.statusLine.statusCode
            if (mHttpEntity != null) {
                mInputStream = mHttpEntity.content
                return "请求状态码: $code\n请求结果:\n${convertStreamToString(mInputStream)}"
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            mInputStream?.close()
        }
        return null
    }
}

fun convertStreamToString(`is`: InputStream): String {
    val reader = BufferedReader(InputStreamReader(`is`))
    val sb = StringBuffer()
    var line: String? = reader.readLine() ?: return sb.toString()
    do {
        sb.append(line + "\n")
        line = reader.readLine()
    } while (line != null)
    return sb.toString()
}