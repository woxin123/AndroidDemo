package online.mengchen.androiddemo.service_demo

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import online.mengchen.androiddemo.MainActivity
import online.mengchen.androiddemo.R

class MyService : Service() {

    private val mBinder = DownloadBinder()

    companion object {
        const val TAG = "MyService"
    }

    /**
     * Service 创建的时候调用
     */
    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate Executed")
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val channel = NotificationChannel("MyService", "MyService", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(this, "MyService")
            .setContentTitle("This is content Title")
            .setContentText("This content text")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    /**
     * Service 每次启动的时候调用
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Executed")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * Service 销毁的时候调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Executed")
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }



    class DownloadBinder: Binder() {
        fun startDownload() {
            Log.d(TAG, "Start Download")
        }

        fun getProcess() {
            Log.d(TAG, "getProcess executed")
        }
    }
}
