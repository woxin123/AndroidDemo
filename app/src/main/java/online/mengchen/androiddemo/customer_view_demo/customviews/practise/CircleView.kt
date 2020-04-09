package online.mengchen.androiddemo.customer_view_demo.customviews.practise

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import online.mengchen.androiddemo.R

class CircleView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        val a = context.obtainStyledAttributes(attr, R.styleable.CircleView)
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED)
        a.recycle()
    }

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )

    private var mColor: Int = Color.RED

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = mColor
        // 考虑 padding 属性
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom
        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom
        val radius = width.coerceAtMost(height) / 2
        canvas.drawCircle((paddingLeft + width / 2).toFloat(), (paddingTop + height / 2).toFloat(), radius.toFloat(), mPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200)
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize)
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200)
        }
    }

}