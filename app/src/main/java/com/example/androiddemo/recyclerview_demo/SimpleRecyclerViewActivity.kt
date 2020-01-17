package com.example.androiddemo.recyclerview_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R

class SimpleRecyclerViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val list: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_recyclerview)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        for (i in 'a'..'z') {
            list.add("Text: $i")
        }
        val adapter = SimpleStringAdapter(this, list)
        recyclerView.adapter = adapter
    }

}
