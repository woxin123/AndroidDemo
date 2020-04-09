package online.mengchen.androiddemo.phone_about

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager

object ScreenUtil {

    fun getScreenSize(context: Context): Pair<Int, Int> {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = wm.defaultDisplay
        val point = Point()
        display.getSize(point)
        return Pair(point.x, point.y)
    }

    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    fun getScreenSizeWithDp(context: Context): Pair<Int, Int> {
        return Pair(context.resources.configuration.screenWidthDp,
            context.resources.configuration.screenHeightDp)
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5F).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5F).toInt()
    }

}