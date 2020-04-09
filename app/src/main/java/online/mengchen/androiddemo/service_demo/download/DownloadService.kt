package online.mengchen.androiddemo.service_demo.download

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import online.mengchen.androiddemo.R
import java.io.File



@RequiresApi(Build.VERSION_CODES.O)
class DownloadService : Service() {

    private var downloadTask: DownloadTask? = null

    private var downloadUrl: String? = null

    private lateinit var notificationChannel: NotificationChannel

    private lateinit var notificationManager: NotificationManager

    private val mBinder = DownLoadBinder()

    override fun onCreate() {
        super.onCreate()
        notificationChannel = NotificationChannel(
            NOTIFICATION_ID,
            NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

    companion object {
        const val NOTIFICATION_ID = "my_id"
        const val NOTIFICATION_NAME = "my_name"


    }

    private val listener = object : DownloadListener {
        override fun onProgress(progress: Int) {
            notificationManager.notify(1, getNotification("Downloading...", progress))
        }

        override fun onSuccess() {
            downloadTask = null
            // 下载成功将前台服务通知关闭
            stopForeground(true)
            notificationManager.notify(1, getNotification("Download Success", -1))
            Toast.makeText(this@DownloadService, "Download Success", Toast.LENGTH_SHORT).show()
        }

        override fun onFailed() {
            downloadTask = null
            stopForeground(true)
            notificationManager.notify(1, getNotification("Download Failed", -1))
            Toast.makeText(this@DownloadService, "Download Failed", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            downloadTask = null
            Toast.makeText(this@DownloadService, "Download Paused", Toast.LENGTH_SHORT).show()
        }

        override fun onCanceled() {
            downloadTask = null
            stopForeground(true)
            Toast.makeText(this@DownloadService, "Canceled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    inner class DownLoadBinder: Binder() {
        fun startDownload(url: String) {
            if (downloadTask == null) {
                downloadUrl = url
                downloadTask = DownloadTask(listener)
                downloadTask?.execute(downloadUrl, this@DownloadService.filesDir.absolutePath)
                startForeground(1, getNotification("Downloading", 0))
                Toast.makeText(this@DownloadService, "Downloading...", Toast.LENGTH_SHORT).show()
            }
        }

        fun pauseDownload() {
            if (downloadTask != null) {
                downloadTask?.pauseDownload()
            }
        }

        fun cancelDownload() {
            if (downloadTask != null) {
                downloadTask?.cancelDowload()
            } else {
                if (downloadUrl != null) {
                    // 取消下载文件时需要将文件删除
                    val fileName = downloadUrl?.substring(downloadUrl?.lastIndexOf("/")!!)
                    val direction = this@DownloadService.filesDir.absolutePath
                    val file = File(direction, fileName)
                    if (file.exists()) {
                        file.delete()
                    }
                    notificationManager.cancel(1)
                    stopForeground(true)
                    Toast.makeText(this@DownloadService, "Cancel", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    private fun getNotification(title: String, progress: Int): Notification {
        val intent = Intent(this, DownloadServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)
        if (progress >= 0) {
            // 当 progress 大于等于 0 时才需显示下载进度
            builder.setContentText("$progress%")
            builder.setProgress(100, progress, false)
        }
        return builder.build()
    }



}