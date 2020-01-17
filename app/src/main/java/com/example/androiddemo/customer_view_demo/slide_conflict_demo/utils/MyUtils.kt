package com.example.androiddemo.customer_view_demo.slide_conflict_demo.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object MyUtils {

    fun getScreenMetrics(context: Context): DisplayMetrics {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm
    }

}