package com.example.kotlin_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_coroutine.R
import com.example.kotlin_coroutine.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import splitties.alertdialog.appcompat.alert
import splitties.alertdialog.appcompat.alertDialog
import splitties.alertdialog.appcompat.coroutines.showAndAwait
import splitties.alertdialog.appcompat.message

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDialogButton.setOnClickListener {
            lifecycleScope.launch {
                val myChoice = alertDialog("Warning!", "Do you want this?")
                toast("My Choice is $myChoice")

                val result = alertDialog {
                    message = "Dialog from Sqlitties"
                }.showAndAwait(
                    okValue = 1,
                    cancelValue = 0,
                    dismissValue = 1
                )
                toast("Result from sqlitties dialog: $result")
            }
        }
    }
}