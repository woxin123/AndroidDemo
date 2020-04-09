package online.mengchen.androiddemo.ipc_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.activity_ipc_third.*

class IPCThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_third)
        btnIPC.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@IPCThirdActivity, IPCDemoActivity::class.java)
            })
        }
    }
}
