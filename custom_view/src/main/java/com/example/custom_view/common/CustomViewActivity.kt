package com.example.custom_view.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.custom_view.MainActivity
import com.example.custom_view.R

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        initView()
    }

    private fun initView() {
        val customViewData = intent.getIntExtra("custom_view_index", 0).let { 
            MainActivity.customViewData[it]
        }
        val customViewTextView = findViewById<TextView>(R.id.custom_view_name)
        val customViewFrameLayout = findViewById<FrameLayout>(R.id.custom_view)
        customViewTextView.text = customViewData.customViewName
        val customView = LayoutInflater.from(this).inflate(customViewData.customViewLayout,
            findViewById(R.id.rootView), false)
        customViewFrameLayout.addView(customView)
        customViewData.initCustomView(customView, this)
    }
}