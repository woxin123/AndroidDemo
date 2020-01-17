package com.example.androiddemo.handler_demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.androiddemo.R

class HandlerDemo2Activity : AppCompatActivity() {

    companion object {
        const val TAG = "HandlerDemo2Activity"
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var start: Button
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo2)
        progressBar = findViewById(R.id.handler2_progress_bar)
        start = findViewById(R.id.btn_handler2_start)

        val updateProgressBar = object: Runnable {
            var i = 0
            override fun run() {
                i += 10
                // 首先获得一个消息
                val message = handler.obtainMessage()
                message.arg1 = i
                Thread.sleep(1000)
                handler.sendMessage(message)
                if (i == 100) {
                    progressBar.removeCallbacks(this)
                }
            }

        }

        handler = Handler { msg ->
            progressBar.progress = msg.arg1
            handler.post(updateProgressBar)
        }

        start.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            handler.post(updateProgressBar)
        }
    }


}
