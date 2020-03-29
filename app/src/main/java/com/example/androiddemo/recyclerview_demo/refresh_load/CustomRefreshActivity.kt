package com.example.androiddemo.recyclerview_demo.refresh_load

import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.example.androiddemo.R
import com.example.androiddemo.recyclerview_demo.SimpleStringAdapter
import java.util.*


class CustomRefreshActivity : AppCompatActivity(), OnRefreshListener, OnLoadMoreListener {

    private lateinit var swipeToLoadLayout: SwipeToLoadLayout
    private lateinit var adapter: SimpleStringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_to_load_layout)
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout)
        val recyclerView: RecyclerView = findViewById(R.id.swipe_target)
        adapter = SimpleStringAdapter(this, mutableListOf("ITEM"))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        swipeToLoadLayout.setOnRefreshListener(this);

        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    override fun onRefresh() {
        swipeToLoadLayout.postDelayed(Runnable {
            swipeToLoadLayout.isRefreshing = false
            adapter.add("REFRESH:\n" + Date())
        }, 2000)
    }

    override fun onLoadMore() {
        swipeToLoadLayout.postDelayed(Runnable {
            swipeToLoadLayout.setLoadingMore(false)
            adapter.add("LOAD MORE:\n" + Date())
        }, 2000)
    }

    private fun autoRefresh() {
        swipeToLoadLayout.post(Runnable { swipeToLoadLayout.isRefreshing = true })
    }
}
