package com.example.androiddemo.handler_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.androiddemo.R

class HandlerInThreadActivity : AppCompatActivity() {

    companion object {
        const val TAG = "HandlerInThreadActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_in_thread)
        var handler: Handler? = null
        Thread {
            Looper.prepare()
            handler = Handler {
                Log.d(TAG, it.data.getString("hello"))
                false
            }
            Looper.loop()
            Log.d(TAG, "我被执行到了")
        }.start()
        while (handler == null) {
            continue
        }
        val message = handler?.obtainMessage()!!
        message.data = Bundle().apply { putString("hello", "我是来自主线程的消息") }
        handler?.sendMessage(message)
    }


}
