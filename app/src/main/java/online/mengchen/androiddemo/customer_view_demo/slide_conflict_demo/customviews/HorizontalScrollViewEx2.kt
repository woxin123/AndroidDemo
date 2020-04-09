package online.mengchen.androiddemo.customer_view_demo.slide_conflict_demo.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class HorizontalScrollViewEx2 :
    ViewGroup {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    companion object {
        const val TAG = "HorizontalScrollViewEx2"
    }

    private var mChildrenSize = 0
    private var mChildWidth = 0
    private var mChildIndex = 0

    // 分别记录上次滑动的坐标
    private var mLastX: Int = 0
    private var mLastY: Int = 0

  // 分别记录上次的滑动坐标（onInterceptTouchEvent）
    private var mLastXIntercept = 0
    private var mLastYIntercept = 0

    private val mScroller: Scroller = Scroller(context)
    private val mVelocityTracker: VelocityTracker = VelocityTracker.obtain()

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x.toInt()
        val y = ev.y.toInt()
        if (ev.action == MotionEvent.ACTION_DOWN) {
            mLastX = x
            mLastY = y
            if (!mScroller.isFinished) {
                mScroller.abortAnimation()
                return true
            }
        } else {
            return true
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "onTouchEvent action: ${event.action}")
        mVelocityTracker.addMovement(event)
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - mLastX
                val deltaY = y - mLastY
                Log.d(TAG, "move deltaX = $deltaX deltaY = $deltaY")
                scrollBy(-deltaX, 0)
            }
            MotionEvent.ACTION_UP -> {
                val scrollX = scrollX
                val scrollToChildIndex = scrollX / mChildWidth
                mVelocityTracker.computeCurrentVelocity(1000)
                val xVelocity = mVelocityTracker.xVelocity
                mChildIndex = if (abs(xVelocity) >= 50) {
                    if (xVelocity > 0) mChildIndex - 1 else mChildIndex + 1
                } else {
                    (scrollX + mChildWidth / 2) / mChildWidth
                }
                mChildIndex = max(0, min(mChildIndex, mChildrenSize - 1))
                val dx = mChildIndex * mChildWidth - scrollX
                smoothScrollBy(dx, 0)
                mVelocityTracker.clear()
                Log.d(TAG, "index: $scrollToChildIndex dx: $dx")
            }
        }

        mLastX = x
        mLastY = y
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var measuredWidth = 0
        var measuredHeight = 0
        val childCount = childCount
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        if (childCount == 0) {
            setMeasuredDimension(0, 0)
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            val childView = getChildAt(0)
            measuredHeight = childView.measuredHeight
            setMeasuredDimension(widthSpaceSize, childView.measuredHeight)
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            val childView = getChildAt(0)
            measuredWidth = childView.measuredWidth
            setMeasuredDimension(childView.measuredWidth, heightSpaceSize)
        } else {
            val childView = getChildAt(0)
            measuredWidth = childView.measuredWidth * childCount
            measuredHeight = childView.measuredHeight
            setMeasuredDimension(measuredWidth, measuredHeight)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        val childCount = childCount
        mChildrenSize = childCount

        for (i in 0 until childCount) {
            val childView: View = getChildAt(i)
            if (childView.visibility != View.GONE) {
                val childWidth = childView.measuredWidth
                mChildWidth = childWidth
                childView.layout(childLeft, 0, childLeft + childWidth,
                    childView.measuredHeight)
                childLeft += childWidth
            }
        }
    }


    private fun smoothScrollBy(dx: Int, dy: Int) {
        mScroller.startScroll(scrollX, 0, dx, 0, 500)
        invalidate()
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }

    override fun onDetachedFromWindow() {
        mVelocityTracker.recycle()
        super.onDetachedFromWindow()
    }
}