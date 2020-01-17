package com.example.androiddemo.customer_view_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.customer_view_demo.common.Constant
import kotlinx.android.synthetic.main.activity_custom_view_menu.*
import kotlinx.android.synthetic.main.custom_view_list_item_layout.view.*

class CustomViewMenuActivity : AppCompatActivity() {

    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_menu)

        init()
    }

    private fun init() {
        myAdapter = MyAdapter()
        with(custom_view_list) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@CustomViewMenuActivity)
        }
    }

    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(layoutInflater.inflate(R.layout.custom_view_list_item_layout, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.customName.text = Constant.APP_MAP.keyAt(position)
            holder.itemView.setOnClickListener {
                startActivity(Intent(this@CustomViewMenuActivity, CustomViewDetialListActivity::class.java).apply {
                    putExtra(CustomViewDetialListActivity.APP_NAME, Constant.APP_MAP.keyAt(position))
                })
            }
        }

        override fun getItemCount(): Int {
            return Constant.APP_MAP.size
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val customName: TextView = itemView.custom_name

        }
    }


}
