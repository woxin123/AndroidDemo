package com.example.custom_view.gesture

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author mengchen
 * @DateTime 2021/4/25 5:28 PM
 */
class GestureImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArray: Int = 0
) : AppCompatImageView(context, attrs, defStyleArray) {
    
    companion object {
        private const val TAG = "GestureImageView"
    }
    
    private val displayMatrix = Matrix()
    
    init {
        scaleType = ScaleType.MATRIX
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
    
    private val gestureOnDoubleTapListener = object : GestureDetector.OnDoubleTapListener {
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
    
    private val scaleGestureListener = object : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            Log.d(TAG, "onScaleBegin")
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            Log.d(TAG, "onScaleEnd")
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            Log.d(TAG, "onScale")
            val scaleFactor = detector.scaleFactor
            val focusX = detector.focusX
            val focusY = detector.focusY
            scaleIfNeed(scaleFactor, focusX, focusY)
            return true
        }
    }

    private val gestureDetector = GestureDetector(context, gestureListener).apply { 
        setOnDoubleTapListener(gestureOnDoubleTapListener)
    }
    
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return true
    }
    
    fun getScale(): Float {
        val f = FloatArray(9)
        displayMatrix.getValues(f)
        return f[Matrix.MSCALE_X]
    }
    
    private fun scaleIfNeed(scaleFactor: Float, focusX: Float, focusY: Float) {
        displayMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY)
        Log.d(TAG, "scale = ${getScale()}")
        imageMatrix = displayMatrix
    }
    

}