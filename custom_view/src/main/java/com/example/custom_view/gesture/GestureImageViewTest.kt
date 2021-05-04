package com.example.custom_view.gesture

import android.app.Activity
import android.view.View
import com.example.custom_view.R
import com.example.custom_view.common.CustomView

/**
 * @Author mengchen
 * @DateTime 2021/4/25 8:19 PM
 */
class GestureImageViewTest : CustomView {
    override val customViewName: String
        get() = "GestureImageView"
    override val customViewLayout: Int
        get() = R.layout.custom_view_gesture_image_view

    override fun initCustomView(view: View, activity: Activity) {
        
    }

}