package com.example.androiddemo.activity_demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.androiddemo.R
import com.example.androiddemo.activity_demo.bean.Person

class ActivityCommunicationActivity : AppCompatActivity() {


    private val button by lazy { findViewById<Button>(R.id.btn_activity_communication_button) }
    private val textView by lazy { findViewById<TextView>(R.id.activity_communication_text_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)
        val person = Person()
        person.username = "张三"
        person.nickname = "小三"
        textView.text = person.toString()
        button.setOnClickListener {
            startActivityForResult(Intent(this, ActivityCommunicationNextActivity::class.java).apply {
                this.putExtras(Bundle().apply {
                    putParcelable("person", person)
                })
            }, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                data?.extras?.getParcelable<Person>("person").let {
                    textView.append("\n 从上一个 Activity 返回的数据")
                    textView.append("\n$it")
                }
            }
        }
    }
}
