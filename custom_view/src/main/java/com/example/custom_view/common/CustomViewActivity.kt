package com.example.custom_view.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.custom_view.R

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        initView()
    }

    private fun initView() {
        val customView = intent.getSerializableExtra("custom_view") as CustomView
        val customViewTextView = findViewById<TextView>(R.id.custom_view_name)
        val customViewFrameLayout = findViewById<FrameLayout>(R.id.custom_view)
        customViewTextView.text = customView.customViewName
        customViewFrameLayout.addView(LayoutInflater.from(this).inflate(customView.customViewLayout,
            findViewById(R.id.rootView), false))
    }
}