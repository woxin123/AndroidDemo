package online.mengchen.androiddemo.customer_view_demo.customviews.practise

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.TextView
import com.nineoldandroids.view.ViewHelper

class TestButton: TextView {

    constructor(context: Context): super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {
        init()
    }

    companion object {
        const val TAG = "TestButton"
    }

    private var mScaledTouchSlop = 0

    // 分别记录上次滑动的坐标
    private var mLastX = 0
    private var mLastY = 0

    fun init() {
        mScaledTouchSlop = ViewConfiguration.get(context)
            .scaledTouchSlop
        Log.d(TAG, "sts: $mScaledTouchSlop")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> { /* return false */  }  // 如果返回 false，那么这个事件就不会交给这个 View 了
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - mLastX
                val deltaY = y - mLastY
                Log.d(TAG, "move, deltaX: $deltaX deltaY = $deltaY")
                val translationX = ViewHelper.getTranslationX(this) + deltaX
                val translationY = ViewHelper.getTranslationY(this) + deltaY
                ViewHelper.setTranslationX(this, translationX)
                ViewHelper.setTranslationY(this, translationY)
            }
            MotionEvent.ACTION_UP -> { }
        }
        mLastX = x
        mLastY = y
        return true
    }

}