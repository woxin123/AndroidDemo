package com.example.kotlin_coroutine.utils

import android.widget.Toast
import com.example.kotlin_coroutine.appContext

inline fun toast(message: Any?) {
    Toast.makeText(appContext, message.toString(), Toast.LENGTH_LONG).show()
}