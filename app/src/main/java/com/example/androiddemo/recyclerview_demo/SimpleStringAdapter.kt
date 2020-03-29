package com.example.androiddemo.recyclerview_demo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R

class SimpleStringAdapter(private val context: Context, private val list: MutableList<String>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(javaClass.simpleName.toUpperCase(), "aaa + ${list[position]}")
        holder.textView.text = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.simple_recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        Log.d(javaClass.simpleName.toUpperCase(), "aaa + ${list.size}")
        return list.size
    }

    fun add(str: String) {
        list.add(str)
        notifyItemChanged(list.size)
    }

}
