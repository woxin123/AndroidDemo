package com.example.androiddemo.ipc_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androiddemo.R
import com.example.androiddemo.ipc_demo.manager.UserManager
import com.example.androiddemo.ipc_demo.model.User
import com.example.androiddemo.ipc_demo.utils.MyConstants
import kotlinx.android.synthetic.main.activity_ipc_second.*
import java.io.File
import java.io.IOException
import java.io.ObjectInputStream

class IPCSecondActivity : AppCompatActivity() {

    companion object {
        const val TAG = "IPCSecondActivity "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_second)
        btnIPC.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@IPCSecondActivity, IPCThirdActivity::class.java)
            })
        }
    }

    override fun onResume() {
        Log.d(TAG, "UserManager.sUserId = ${UserManager.sUserId}")
        recoverFromFile()
        super.onResume()
    }

    fun recoverFromFile() {
        Thread {
            var user: User? = null
            val cacheFile = File(MyConstants.CACHE_FILE_PATH)
            if (cacheFile.exists()) {
                var objectInputStream: ObjectInputStream? = null
                try {
                    objectInputStream = ObjectInputStream(cacheFile.inputStream())
                    user = objectInputStream.readObject()!! as User
                    Log.d(TAG, "recover user: $user")
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    objectInputStream?.close()
                }
            }
        }.start()
    }
}
