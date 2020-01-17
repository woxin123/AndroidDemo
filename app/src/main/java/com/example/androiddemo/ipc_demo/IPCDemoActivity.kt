package com.example.androiddemo.ipc_demo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.androiddemo.R
import com.example.androiddemo.ipc_demo.aidl.BookManagerActivity
import com.example.androiddemo.ipc_demo.messenger.MessengerActivity
import com.example.androiddemo.ipc_demo.manager.UserManager
import com.example.androiddemo.ipc_demo.model.User
import com.example.androiddemo.ipc_demo.utils.MyConstants
import kotlinx.android.synthetic.main.activity_ipcdemo.*
import java.io.File
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.OutputStream

class IPCDemoActivity : AppCompatActivity() {

    companion object {
        const val TAG = "IPCDemoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipcdemo)
        UserManager.sUserId = 2
        btn_ipc_1.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@IPCDemoActivity, IPCSecondActivity::class.java)
            })
        }
        btnMessenger.setOnClickListener {
            startActivity(Intent(this, MessengerActivity::class.java))
        }
        btnBookManager.setOnClickListener {
            startActivity(Intent(this, BookManagerActivity::class.java))
        }
    }

    override fun onResume() {
        Log.d(TAG, "UserManager.sUserId = ${UserManager.sUserId}")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            persistToFile()
        }
        super.onResume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    persistToFile()
                } else {
                    Toast.makeText(this, "You Denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun persistToFile() {
        Thread {
            val user = User(1, "hello world", false)
            val dir = File(MyConstants.PATH)
            if (!dir.exists()) {
                dir.mkdir()
            }
            Log.d(TAG, dir.absolutePath)
            val cacheFile = File(MyConstants.CACHE_FILE_PATH)
            var outputStream: OutputStream? = null
            var objectOutputStream: ObjectOutputStream? = null
            try {
                outputStream = cacheFile.outputStream()
                objectOutputStream = ObjectOutputStream(outputStream)
                objectOutputStream.writeObject(user)
                objectOutputStream.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                outputStream?.close()
                objectOutputStream?.close()
            }
        }.start()
    }
}
