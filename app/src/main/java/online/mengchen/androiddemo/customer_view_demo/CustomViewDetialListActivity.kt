package online.mengchen.androiddemo.customer_view_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.customer_view_demo.common.Constant
import kotlinx.android.synthetic.main.activity_custom_view_detial_list.*
import kotlinx.android.synthetic.main.custom_view_list_item_layout.view.*

class CustomViewDetialListActivity : AppCompatActivity() {

    companion object {
        const val APP_NAME = "app_name"
    }

    private lateinit var appName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_detial_list)
        init()
    }

    private fun init() {
        appName = intent.getStringExtra(APP_NAME)
        with(custom_app_list) {
            adapter = MyAdapter(Constant.APP_MAP[appName]!!)
            layoutManager = LinearLayoutManager(this@CustomViewDetialListActivity)
        }
    }

    inner class MyAdapter(private val customViewNameList: ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(layoutInflater.inflate(R.layout.custom_view_list_item_layout, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tvName.text = customViewNameList[position]
            holder.itemView.setOnClickListener {
                startActivity(Intent(this@CustomViewDetialListActivity, CustomDetailActivity::class.java).apply {
                    putExtra(APP_NAME, appName)
                    putExtra(CustomDetailActivity.CUSTOM_VIEW_NAME, Constant.APP_MAP[appName]?.get(position))
                })
            }
        }

        override fun getItemCount(): Int {
            return customViewNameList.size
        }

        inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.custom_name
        }
    }
}
