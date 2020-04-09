package online.mengchen.androiddemo.handler_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.R

class HandlerDemoMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var handler1: Button
    private lateinit var handler2: Button
    private lateinit var handler3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo_menu)
        handler1 = findViewById(R.id.btn_handler1)
        handler1.setOnClickListener(this)
        handler2 = findViewById(R.id.btn_handler2)
        handler2.setOnClickListener(this)
        handler3 = findViewById(R.id.btn_handler3)
        handler3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_handler1 -> {
                startActivity(Intent(this, HandlerDemo1Activity::class.java))
            }
            R.id.btn_handler2 -> {
                startActivity(Intent(this, HandlerDemo2Activity::class.java))
            }
            R.id.btn_handler3 -> {
                startActivity(Intent(this, HandlerInThreadActivity::class.java))
            }
        }
    }
}
