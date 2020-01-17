package com.example.androiddemo

import android.app.Application
import android.content.Context
import android.os.StrictMode
import androidx.multidex.MultiDex

class MyApplication: Application() {

    /**
     * 这个类可以将应用初始化提前，在 super.attachBaseContext(base) 之后使用 context 做一些事情
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 为了解决 Android 5.0 之下的 65535 限制
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
//        StrictMode.ThreadPolicy.Builder()
//            .detectAll()
//            .penaltyLog()
//            .build()
//        StrictMode.VmPolicy.Builder()
//            .detectAll()
//            .penaltyLog()
//            .build()
    }

}