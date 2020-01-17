package com.example.androiddemo.customer_view_demo.slide_conflict_demo

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.androiddemo.R
import com.example.androiddemo.customer_view_demo.slide_conflict_demo.customviews.HorizontalScrollViewEx2
import com.example.androiddemo.customer_view_demo.slide_conflict_demo.customviews.ListViewEx
import com.example.androiddemo.customer_view_demo.slide_conflict_demo.utils.MyUtils

class SlideConflictDemo2Activity : AppCompatActivity() {

    companion object {
        const val TAG = "SlideConflictDemo2Activity"
    }

    private lateinit var mListContainer: HorizontalScrollViewEx2

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_conflict_demo2)
        Log.d(TAG, "onCreate")
        initView()
    }

    private fun initView() {
        mListContainer = findViewById(R.id.container)
        val screenWidth = MyUtils.getScreenMetrics(this).widthPixels
        val screenHeight = MyUtils.getScreenMetrics(this).heightPixels
        for (i in 0 until 5) {
            val layout = layoutInflater.inflate(R.layout.content_layout2, mListContainer, false)
                    as ViewGroup
            layout.layoutParams.width = screenWidth
            val textView: TextView = layout.findViewById(R.id.title)
            textView.text = "page ${i + 1}"
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0))
            createList(layout)
            mListContainer.addView(layout)
        }
    }

    private fun createList(layout: ViewGroup) {
        val listView = layout.findViewById<ListViewEx>(R.id.list)
        val datas = ArrayList<String>()
        for (i in 0..49) {
            datas.add("name $i")
        }
        val adapter = ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, datas)
        listView.mHorizontalScrollViewEx2 = mListContainer
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, _, id ->
            Toast.makeText(this, "click item", Toast.LENGTH_SHORT).show()
        }
    }
}
