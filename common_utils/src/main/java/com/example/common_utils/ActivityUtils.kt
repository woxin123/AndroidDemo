package com.example.common_utils

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.ContextWrapper

/**
 * @Author mengchen
 * @DateTime 2021/5/4 5:01 PM
 */
fun Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> this.baseContext.getActivity()
        else -> null
    }
}
        