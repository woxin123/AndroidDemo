package com.example.custom_view.gesture

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
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

    companion object {
        private val TAG = "GestureLayout"
    }
    
    private val gestureListener = object : GestureDetector.OnGestureListener {
        override fun onShowPress(e: MotionEvent?) {
            Log.d(TAG, "onShowPress")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.d(TAG, "onSingleTapUp")
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            Log.d(TAG, "onDown")
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d(TAG, "onFling")
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.d(TAG, "onScroll")
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.d(TAG, "onLongPress")
        }
    }
    
    private val onDoubleTapListener = object : GestureDetector.OnDoubleTapListener {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Log.d(TAG, "onDoubleTap")
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            Log.d(TAG, "onDoubleTapEvent")
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            Log.d(TAG, "onSingleTapConfirmed")
            return true
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