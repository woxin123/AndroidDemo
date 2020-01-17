package com.example.androiddemo.animation_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.androiddemo.R

class AnimationMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rotationAnimation: Button
    private lateinit var scaleAnimation: Button
    private lateinit var activitySwitchAnimation: Button
    private lateinit var lottieAnimation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_menu)
        rotationAnimation = findViewById(R.id.btn_simple_animation_rotation)
        scaleAnimation = findViewById(R.id.btn_simple_animation_scale)
        activitySwitchAnimation = findViewById(R.id.btn_activity_switch)
        lottieAnimation = findViewById(R.id.btn_lottie_animation)
        rotationAnimation.setOnClickListener(this)
        scaleAnimation.setOnClickListener(this)
        activitySwitchAnimation.setOnClickListener(this)
        lottieAnimation.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_simple_animation_rotation -> {
                val intent = Intent(this, SimpleAnimationActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_simple_animation_scale -> {
                val intent = Intent(this, SimpleAnimationScaleActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_activity_switch -> {
                val intent = Intent(this, ActivitySwitchAnimActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_lottie_animation -> {
                val intent = Intent(this, LottieAnimationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
