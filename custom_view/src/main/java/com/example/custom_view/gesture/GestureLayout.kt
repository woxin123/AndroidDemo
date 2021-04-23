package com.example.custom_view.gesture

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

/**
 * @Author mengchen
 * @DateTime 2021/4/22 9:29 PM
 */
class GestureLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    
    private val gestureListener = object : GestureDetector.OnGestureListener {
        override fun onShowPress(e: MotionEvent?) {
            TODO("Not yet implemented")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            TODO("Not yet implemented")
        }

        override fun onDown(e: MotionEvent?): Boolean {
            TODO("Not yet implemented")
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onLongPress(e: MotionEvent?) {
            TODO("Not yet implemented")
        }
    }
    
    private val onDoubleTapListener = object : GestureDetector.OnDoubleTapListener {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            TODO("Not yet implemented")
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            TODO("Not yet implemented")
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            TODO("Not yet implemented")
        }

    }
    
    private val gestureDetector = GestureDetector(context, gestureListener)
    
    init {
        gestureDetector.setOnDoubleTapListener(onDoubleTapListener)
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
    
    
}