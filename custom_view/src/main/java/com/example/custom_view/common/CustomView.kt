package com.example.custom_view.common

import android.app.Activity
import android.os.Parcelable
import android.view.View
import java.io.Serializable

interface CustomView :  Serializable {

    val customViewName: String
    val customViewLayout: Int

    fun initCustomView(view: View, activity: Activity)

}