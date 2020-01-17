package com.example.androiddemo.architecture_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemo.R
import com.example.androiddemo.architecture_demo.mvc.LoadDataActivity
import com.example.androiddemo.architecture_demo.mvp.LoginActivity
import com.example.androiddemo.architecture_demo.mvvm.ChangeAgeActivity
import kotlinx.android.synthetic.main.activity_architecture_menu.*

class ArchitectureMenuActivity : AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture_menu)
        R.id.btn_architecture_mvc.run {
            btn_architecture_mvc.setOnClickListener(this@ArchitectureMenuActivity)
        }
        R.id.btn_architecture_mvp.run {
            btn_architecture_mvp.setOnClickListener(this@ArchitectureMenuActivity)
        }
        R.id.btn_architecture_mvvm.run {
            btn_architecture_mvvm.setOnClickListener(this@ArchitectureMenuActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_architecture_mvc -> Intent(this, LoadDataActivity::class.java)
            R.id.btn_architecture_mvp -> Intent(this, LoginActivity::class.java)
            R.id.btn_architecture_mvvm -> Intent(this, ChangeAgeActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }
}
