package online.mengchen.androiddemo.handler_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import online.mengchen.androiddemo.R

class HandlerDemo1Activity : AppCompatActivity() {

    private val handler: Handler = Handler()
    private lateinit var start: Button
    private lateinit var end:   Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo1)
        textView = findViewById(R.id.handler1_textView)
        textView.append("ThreadId = ${Thread.currentThread().id}")
        start = findViewById(R.id.btn_handler1_start)
        end = findViewById(R.id.btn_handler1_end)
        val updateThread = object: Runnable {
            override fun run() {
                textView.append("\nUpdateThread....")
//                Thread.sleep(1000)
                textView.append("\nThreadId = ${Thread.currentThread().id}")
                handler.postDelayed(this, 1000)
            }

        }
        start.setOnClickListener {
            handler.postDelayed(updateThread, 1000)
        }
        end.setOnClickListener {
            handler.removeCallbacks(updateThread)
        }
    }


}
