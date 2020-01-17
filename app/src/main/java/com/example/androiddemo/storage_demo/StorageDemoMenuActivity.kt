package com.example.androiddemo.storage_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.androiddemo.R
import com.example.androiddemo.storage_demo.content_provider_demo.ContentProviderDemoMenuActivity

class StorageDemoMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedPreference: Button
    private lateinit var dataBase: Button
    private lateinit var contentProvider: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_demo_menu)
        sharedPreference = findViewById(R.id.btn_storage_share_preference)
        sharedPreference.setOnClickListener(this)
        dataBase = findViewById(R.id.btn_storage_data_base)
        dataBase.setOnClickListener(this)
        contentProvider = findViewById(R.id.btn_storage_content_provider)
        contentProvider.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_storage_share_preference -> {
                startActivity(Intent(this, SharedPreferenceActivity::class.java))
            }
            R.id.btn_storage_data_base -> {
                startActivity(Intent(this, DataBaseDemoActivity::class.java))
            }
            R.id.btn_storage_content_provider -> {
                startActivity(Intent(this, ContentProviderDemoMenuActivity::class.java))
            }
        }
    }
}
