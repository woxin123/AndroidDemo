package com.example.androiddemo.service_demo.download

import android.Manifest
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
import androidx.core.content.ContextCompat
import com.example.androiddemo.R
import com.example.androiddemo.service_demo.MyService

class DownloadServiceActivity : AppCompatActivity(), View.OnClickListener {

    private var downloadBinder: DownloadService.DownLoadBinder? = null

    private val connection = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("aaa", "绑定成功")
            downloadBinder = service as DownloadService.DownLoadBinder
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_service)
        findViewById<Button>(R.id.btn_service_download_start_download).setOnClickListener(this)
        findViewById<Button>(R.id.btn_service_download_pause_download).setOnClickListener(this)
        findViewById<Button>(R.id.btn_service_download_cancel_download).setOnClickListener(this)
        val intent = Intent(this, DownloadService::class.java)
        startService(intent)
        bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定服务
    }

    override fun onClick(v: View?) {
        if (downloadBinder == null) {
            return
        }
        when (v?.id) {
            R.id.btn_service_download_start_download -> {
                val url = "https://ichef.bbci.co.uk/news/660/cpsprodpb/C120/production/_104304494_mediaitem104304493.jpg"
                downloadBinder?.startDownload(url)
            }
            R.id.btn_service_download_pause_download -> {
                downloadBinder?.pauseDownload()
            }
            R.id.btn_service_download_cancel_download -> {
                downloadBinder?.cancelDownload()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}
