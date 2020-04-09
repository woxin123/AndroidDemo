package online.mengchen.androiddemo.service_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.MainActivity
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.service_demo.download.DownloadServiceActivity

class MyServiceActivity : AppCompatActivity(), View.OnClickListener {

    private val start by lazy { findViewById<Button>(R.id.btn_service_start) }
    private val stop by lazy { findViewById<Button>(R.id.btn_service_stop) }
    private val bind by lazy { findViewById<Button>(R.id.btn_service_bind) }
    private val unBind by lazy { findViewById<Button>(R.id.btn_service_unbind) }
    private val startIntentService by lazy { findViewById<Button>(R.id.btn_service_intent_service_start) }
    private val downloadService by lazy { findViewById<Button>(R.id.btn_service_download_service) }

    private lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProcess()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_service)
        start.setOnClickListener(this)
        stop.setOnClickListener(this)
        bind.setOnClickListener(this)
        bind.setOnClickListener(this)
        unBind.setOnClickListener(this)
        startIntentService.setOnClickListener(this)
        downloadService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_service_start -> {
                val startIntent = Intent(this, MyService::class.java)
                startService(startIntent)   // 开启服务
            }
            R.id.btn_service_stop -> {
                val stopIntent = Intent(this, MyService::class.java)
                stopService(stopIntent)
            }
            R.id.btn_service_bind -> {
                val bindIntent = Intent(this, MyService::class.java)
                bindService(bindIntent, connection, Context.BIND_AUTO_CREATE)
            }
            R.id.btn_service_unbind -> {
                unbindService(connection)
            }
            R.id.btn_service_intent_service_start -> {
                // 打印主进程 id
                Log.d("MyServiceActivity", "Thread id is ${Thread.currentThread().id}")
                val intentService = Intent(this, MyIntentService::class.java)
                startService(intentService)
            }
            R.id.btn_service_download_service -> {
                startActivity(Intent(this, DownloadServiceActivity::class.java))
            }
        }
    }
}
