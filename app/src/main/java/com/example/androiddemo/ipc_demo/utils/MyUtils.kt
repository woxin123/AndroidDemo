package com.example.androiddemo.ipc_demo.utils

import android.app.ActivityManager
import android.content.Context

object MyUtils {
    fun getProcessName(ctx: Context, pid: Int): String? {
        val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }

}