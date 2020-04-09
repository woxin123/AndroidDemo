package online.mengchen.androiddemo.ui_demo.surface_demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import java.lang.Exception
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.O)
class MySurfaceView: SurfaceView, SurfaceHolder.Callback, Runnable {

    constructor(context: Context): super(context) { initView() }
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) { initView() }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) { initView() }

    private val mHolder: SurfaceHolder = holder

    // 用于绘图的 canvas
    private lateinit var mCanvas: Canvas

    // 子线程标志位
    private var mIsDrawing: Boolean = false

    var x = 0
    var y = 400
    private val mPath = Path()
    private val mPaint = Paint()

    private fun initView() {
        mHolder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        this.keepScreenOn = true
        mPath.moveTo(x.toFloat(), y.toFloat())
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10F
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mIsDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
    }

    override fun run() {
        while (mIsDrawing) {
            draw()
            x += 1
            y = (100 * sin(x * 2 * Math.PI / 180) + 400).toInt()
            mPath.lineTo(x.toFloat(), y.toFloat())
        }
    }

    private fun draw() {
        try {
            mCanvas = mHolder.lockCanvas()
            mCanvas.drawColor(Color.WHITE)
            mCanvas.drawPath(mPath, mPaint)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mHolder.unlockCanvasAndPost(mCanvas)
        }
    }

}