package online.mengchen.androiddemo.customer_view_demo.customviews.practise

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CounterView(context: Context, attr: AttributeSet): View(context, attr), View.OnClickListener {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBounds: Rect = Rect()
    private var mCount: Int = 0

    init {
        setOnClickListener(this)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = Color.BLUE
        canvas.drawRect(0.0F, 0.0F, width.toFloat(), height.toFloat(), mPaint)
        mPaint.color = Color.YELLOW
        mPaint.textSize = 90F
        val text = mCount.toString()
        mPaint.getTextBounds(text, 0, text.length, mBounds)
        val textWidth = mBounds.width()
        val textHeight = mBounds.height()
        canvas.drawText(text, (width / 2 - textWidth / 2).toFloat(),
            (height / 2 + textHeight / 2).toFloat(), mPaint)
    }

    override fun onClick(v: View?) {
        mCount++
        invalidate()
    }

}