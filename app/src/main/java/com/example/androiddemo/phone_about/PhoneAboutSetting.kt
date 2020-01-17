package com.example.androiddemo.phone_about

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.example.androiddemo.R

object PhoneAboutSetting {


    fun setting(view: View, activity: Activity) {
        val (width, height) = ScreenUtil.getScreenSize(activity)
        val statusHeight = ScreenUtil.getStatusBarHeight(activity)
        val navigationHeight = ScreenUtil.getNavigationBarHeight(activity)
        ViewCompat.requireViewById<TextView>(view, R.id.tv_phone_width).append(width.toString())
        ViewCompat.requireViewById<TextView>(view, R.id.tv_phone_height).append(height.toString())
        ViewCompat.requireViewById<TextView>(view, R.id.tv_status_bar_width).append(statusHeight.toString())
        ViewCompat.requireViewById<TextView>(view, R.id.tv_navigation_bar_height).append(navigationHeight.toString())
    }

}