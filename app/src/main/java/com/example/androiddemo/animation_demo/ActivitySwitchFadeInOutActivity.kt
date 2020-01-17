package com.example.androiddemo.animation_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddemo.R

class ActivitySwitchFadeInOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_fade_in_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
