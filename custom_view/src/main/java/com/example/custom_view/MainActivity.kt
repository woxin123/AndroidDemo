package com.example.custom_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_view.common.CustomView
import com.example.custom_view.common.CustomViewActivity
import com.example.custom_view.gesture.GestureLayoutTest

class MainActivity : AppCompatActivity() {

    private val customViewData = mutableListOf<CustomView>()

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        customViewData.add(GestureLayoutTest())
    }

    private fun initView() {
        recyclerView.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_custom_view_button, parent, false))
            }

            override fun getItemCount(): Int {
                return customViewData.size
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.button.text = customViewData[position].customViewName
                holder.button.setOnClickListener {
                    startActivity(Intent(this@MainActivity, CustomViewActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putSerializable("custom_view", customViewData[position])
                        })
                    })
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button = itemView.findViewById<Button>(R.id.custom_view_button)
    }
}