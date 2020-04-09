package online.mengchen.androiddemo.activity_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.activity_demo.*

class ActivityDemoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        btn_activity_communication.setOnClickListener(this)
        btn_activity_restart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_activity_communication -> Intent(this, ActivityCommunicationActivity::class.java)
            R.id.btn_activity_restart -> Intent(this, ActivityRestartActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }

}
