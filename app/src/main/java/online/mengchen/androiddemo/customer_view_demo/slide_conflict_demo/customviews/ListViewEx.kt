package online.mengchen.androiddemo.customer_view_demo.slide_conflict_demo.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ListView
import kotlin.math.abs

class ListViewEx : ListView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    companion object {
        const val TAG = "ListViewEx"
    }

    var mHorizontalScrollViewEx2: HorizontalScrollViewEx2? = null

    // 分别记录上次滑动的坐标
    private var mLastX = 0
    private var mLastY = 0

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x.toInt()
        val y = ev.y.toInt()
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mHorizontalScrollViewEx2?.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - mLastX
                val deltaY = y - mLastY
                Log.d(TAG, "dx: $deltaX dy: $deltaY")
                if (abs(deltaX) > abs(deltaY)) {
                    mHorizontalScrollViewEx2?.requestDisallowInterceptTouchEvent(false)
                }
            }
            MotionEvent.ACTION_UP -> { }
        }
        mLastX = x
        mLastY = y
        return super.dispatchTouchEvent(ev)
    }


}