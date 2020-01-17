package com.example.androiddemo.test_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_test_demo.*

class TestDemoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_demo)
        btn_test_event_bus.run {
            setOnClickListener(this@TestDemoActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_test_event_bus -> Intent(this, EventBusTestDemoActivity::class.java)
            else -> null
        }?.let {
            startActivity(it)
        }
    }
}
