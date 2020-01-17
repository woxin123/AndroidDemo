package com.example.androiddemo.storage_demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.androiddemo.R

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    companion object {
        const val FILE_NAME = "SHARED"
        const val KEY = "KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preference)
        editText = findViewById(R.id.storage_share_preference_edit_text)
        val sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val input = sharedPreferences.getString(KEY, "")
        editText.append(input)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onDestroy() {
        super.onDestroy()
        val input = editText.text.toString()
        if (input.trim() != "") {
            val sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(KEY, input)
            editor.commit()
        }
    }

}
