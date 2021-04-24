package com.example.custom_view.gesture

import android.view.View
import com.example.custom_view.R
import com.example.custom_view.common.CustomView

class GestureLayoutTest : CustomView {
    override val customViewName: String
        get() = "GestureLayout"
    override val customViewLayout: Int
        get() = R.layout.custom_view_gesture_layout

    override fun initCustomView(view: View) {
    }

}