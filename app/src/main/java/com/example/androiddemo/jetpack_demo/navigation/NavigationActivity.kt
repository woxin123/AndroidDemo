package com.example.androiddemo.jetpack_demo.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.example.androiddemo.R

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        Log.d("mengchen", "aaa")
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.my_nav_host_fragment).navigateUp()
}
