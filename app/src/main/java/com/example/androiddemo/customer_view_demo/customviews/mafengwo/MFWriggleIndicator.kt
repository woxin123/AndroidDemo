package com.example.androiddemo.customer_view_demo.customviews.mafengwo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.Display
import android.view.View
import com.example.androiddemo.customer_view_demo.common.DisplayUtils
import kotlin.math.PI
import kotlin.math.sin

class MFWriggleIndicator : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint: Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    // sin 曲线的最高点
    private val sinYMax: Float by lazy { DisplayUtils.dp2px(context, 3.0F).toFloat() }

    // sin 曲线路径
    private val sinPath: Path by lazy { Path() }

    // 绘制点的个数
    private val pointNum = 20

    // 蠕动的百分比0-1
    private var percent = 0.0F

    private val dexY = DisplayUtils.dp2px(context, 1.0F)
    private val dexX = DisplayUtils.dp2px(context, 3.0F)

    // 控件高度
    private val viewHeight = DisplayUtils.dp2px(context, sinYMax * 2.0F + dexY * 2)
    private val viewWidth = DisplayUtils.dp2px(context, 1.5F) * dexX * 2

    // x轴的上下偏移量，使 x 轴在中间
    private val xOffset by lazy { viewHeight / 2.0F }

    init {
        with(paint) {
            strokeCap = Paint.Cap.ROUND
            strokeWidth = DisplayUtils.dp2px(context, 2.5F).toFloat()
            color = Color.parseColor("#EFD66D")
            style = Paint.Style.STROKE
        }
    }

    fun setPercentNum(percentNum: Float) {
        percent = percentNum
        postInvalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onDraw(canvas: Canvas) {
        sinPath.reset()
        for (i in 0..pointNum) {
            if (i == 0) sinPath.moveTo(dexX.toFloat(), calY(i))
            sinPath.lineTo(dexX + (viewWidth - dexX * 2) / 20.0F * i, calY(i))
        }
        canvas.drawPath(sinPath, paint)
    }

    private fun calY(i: Int) =
        (sin(i.toFloat() / pointNum * PI + 2.0F * PI * percent) * sinYMax + xOffset).toFloat()

}