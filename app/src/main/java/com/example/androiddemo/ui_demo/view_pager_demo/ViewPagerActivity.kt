package com.example.androiddemo.ui_demo.view_pager_demo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.androiddemo.R
import org.w3c.dom.Text

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var imageView: ImageView
    private var list = mutableListOf<View>()
    private var offSet: Int = 0
    private var currentItem: Int = 0
    private var matrix: Matrix = Matrix()
    private var bmWidth: Int = 0
    private lateinit var animation: Animation
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        imageView = findViewById(R.id.view_pager_cursor)
        textView1 = findViewById(R.id.view_pager_textView1)
        textView2 = findViewById(R.id.view_pager_textView2)
        textView3 = findViewById(R.id.view_pager_textView3)

        // 操作子 layout 的时候可以直接使用 v1
        val v1 = layoutInflater.inflate(R.layout.layout_view_pager1, null)
        list.add(v1)
        val v2 = layoutInflater.inflate(R.layout.layout_view_pager2, null)
        list.add(v2)
        val v3 = layoutInflater.inflate(R.layout.layout_view_pager3, null)
        list.add(v3)

        // 初始化滑动图片的位置
        initCursor()

        val adapter = ViewPagerAdapter(list)
        viewPager = findViewById(R.id.view_pager_view_pager)
        viewPager.adapter = adapter
        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.d(javaClass.simpleName.toUpperCase(), state.toString())
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d(javaClass.simpleName.toUpperCase(), position.toString())
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        if (currentItem == 1) {
                            animation =  TranslateAnimation ((offSet * 2 + bmWidth).toFloat(), 0F, 0F, 0F)
                        } else if (currentItem == 2) {
                            animation =  TranslateAnimation ((offSet * 4 + 2 * bmWidth).toFloat(), 0F, 0F, 0F)
                        }
                    }
                    1 -> {
                        if (currentItem == 0) {
                            animation = TranslateAnimation (0F, (offSet * 2+bmWidth).toFloat(), 0F, 0F)
                        } else if (currentItem == 2) {
                            animation =
                                TranslateAnimation ((4 * offSet + 2 * bmWidth).toFloat(),
                                    (offSet * 2 + bmWidth).toFloat(), 0F, 0F)
                        }
                    }
                    2 -> {
                        if (currentItem == 0) {
                            animation =  TranslateAnimation (0F,
                                (4 * offSet+2 * bmWidth).toFloat(), 0F, 0F)
                        } else if (currentItem == 1) {
                            animation =
                                TranslateAnimation ((offSet * 2 + bmWidth).toFloat(),
                                    (4 * offSet+2 * bmWidth).toFloat(), 0F, 0F)
                        }
                    }
                }
                currentItem = position
                animation.duration = 150 // 光标滑动速度
                animation.fillAfter = true
                imageView.startAnimation(animation)
            }

        })

        textView1.setOnClickListener {
            viewPager.currentItem = 0
        }

        textView2.setOnClickListener {
            viewPager.currentItem = 1
        }

        textView3.setOnClickListener {
            viewPager.currentItem = 2
        }

    }

    /**
     * 计算滑动的图片的位置
     */
    fun initCursor() {
        bmWidth = imageView.width

        val dm: DisplayMetrics = resources.displayMetrics
        // 设置图标的起始位置(3:3个textview。)
        offSet = (dm.widthPixels - 3 * bmWidth) / 6
        // offSet = dm.widthPixels / 6 - bmWidth / 3
        matrix.setTranslate(offSet.toFloat(), 0F)
        imageView.imageMatrix = matrix // 需要imageView的scaleType为matrix
        currentItem = 0
    }
}
