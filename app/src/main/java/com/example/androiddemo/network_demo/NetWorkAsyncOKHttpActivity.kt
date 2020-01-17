package com.example.androiddemo.network_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.androiddemo.R
import okhttp3.*
import java.io.IOException


class NetWorkAsyncOKHttpActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var get: Button
    private lateinit var post: Button
    private lateinit var result: TextView
    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work_async_okhttp)
        editText = findViewById(R.id.net_work_async_okhttp_edit_text)
        get = findViewById(R.id.btn_net_work_async_okhttp_get)
        post = findViewById(R.id.btn_net_work_async_okhttp_post)
        result = findViewById(R.id.net_work_async_okhttp_result)


        get.setOnClickListener {
            val url = editText.text.toString()
            if (url.trim() == "") {
                Toast.makeText(this, "网址不能为空", Toast.LENGTH_SHORT).show()
            } else {
                getAsyncHttp(url)
            }
        }
    }

    private fun getAsyncHttp(url: String) {
        val request = Request.Builder().url(url).build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val res = convertStreamToString(response.body!!.byteStream())
                runOnUiThread {
                    result.text = res
                }
            }

        })
    }
}
