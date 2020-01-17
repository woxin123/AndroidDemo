package com.example.androiddemo.customer_view_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemo.R
import com.example.androiddemo.customer_view_demo.common.Constant
import com.example.androiddemo.customer_view_demo.slide_conflict_demo.SlideConflictDemo1Activity
import com.example.androiddemo.customer_view_demo.slide_conflict_demo.SlideConflictDemo2Activity
import kotlinx.android.synthetic.main.activity_custom_detail.*

class CustomDetailActivity : AppCompatActivity() {

    companion object {
        const val CUSTOM_VIEW_NAME = "custom_view_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_detail)
        val appName = intent.getStringExtra(CustomViewDetialListActivity.APP_NAME)
        val customViewName = intent.getStringExtra(CUSTOM_VIEW_NAME)

        // 自定义 view 的 layout
        var viewId = 0

        when (appName) {
            // get
            Constant.APP_PRACTISE -> when (customViewName) {
                Constant.COUNTER -> {
                    viewId = R.layout.activity_counter_view
                }
                Constant.CIRCLE -> {
                    viewId = R.layout.layout_custom_view_circle
                }
                Constant.TEST_BUTTON -> {
                    viewId = R.layout.custom_test_button
                }
            }
            Constant.APP_GET -> when (customViewName) {
                // 发布内容弹出效果
                Constant.GET_CUSTOM_PUBLISH -> viewId = R.layout.custom_get_publish_view

            }
            Constant.APP_SLIDE_CONFLICAT -> when (customViewName) {
                Constant.SLIDE_CONFLICT_DEMO1 -> {
                    startActivity(Intent(this, SlideConflictDemo1Activity::class.java))
                    return
                }
                Constant.SLIDE_CONFLICT_DEMO2 -> {
                    startActivity(Intent(this, SlideConflictDemo2Activity::class.java))
                    return
                }
            }
            Constant.APP_MFW -> {
                when (customViewName) {
                    Constant.MFW_WRIGGLE_TAB -> {
                        viewId = R.layout.custom_view_mfw_wriggle_tab_view
                    }
                }
            }
        }
        // 添加到当前布局中
        if (viewId != 0) {
            View.inflate(this, viewId, container)
        }
    }
}
