package online.mengchen.androiddemo.ipc_demo

import android.app.Application
import android.os.Process
import android.util.Log
import online.mengchen.androiddemo.ipc_demo.utils.MyUtils

class MyApplication: Application() {
    companion object {
        const val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()
        val processName = MyUtils.getProcessName(this, Process.myPid())
        Log.d(TAG, "application start, process name: $processName")
    }

}