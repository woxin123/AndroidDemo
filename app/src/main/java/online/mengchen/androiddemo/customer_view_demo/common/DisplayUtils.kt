package online.mengchen.androiddemo.customer_view_demo.common

import android.content.Context

object DisplayUtils {

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5F).toInt()
    }

    fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5F).toInt()
    }

}