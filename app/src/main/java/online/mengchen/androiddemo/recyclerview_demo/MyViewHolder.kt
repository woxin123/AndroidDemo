package online.mengchen.androiddemo.recyclerview_demo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import online.mengchen.androiddemo.R

class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val textView: TextView = v.findViewById(R.id.simple_recyclerview_textview)

}