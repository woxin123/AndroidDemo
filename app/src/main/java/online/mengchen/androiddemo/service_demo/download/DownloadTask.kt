package online.mengchen.androiddemo.service_demo.download

import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile

class DownloadTask(val downloadListener: DownloadListener): AsyncTask<String, Int, Int>() {


    companion object {
        const val TYPE_SUCCESS = 0
        const val TYPE_FAILED = 1
        const val TYPE_PAUSE = 2
        const val TYPE_CANCELED = 3
    }

    private var isPause: Boolean = false
    private var isCanceled: Boolean = false
    private var lastProgress: Int = 0
    private val okHttpClient = OkHttpClient()

    override fun doInBackground(vararg params: String?): Int {
        var iis: InputStream? = null
        var saveFile: RandomAccessFile? = null
        var file: File? = null
        try {
            var downloadLength = 0L  // 记录下载文件的长度
            val downloadUrl = params[0]!!
            val fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"))
            val directory = params[1]!!
            Log.d("DownloadTask", directory)
            file = File(directory, fileName)
            if (file.exists()) {
                downloadLength = file.length()
            }
            val contentLength = getContentLength(downloadUrl)
            if (contentLength == 0L) {
                return TYPE_FAILED
            }
            if (contentLength == downloadLength) {
                // 已下载字节和文件总字节数相等，说明已经下载完成
                return TYPE_SUCCESS
            }
            val request = Request.Builder()
                    // 断点下载，指定从哪个字节开始下载
                .addHeader("RANGE", "byte=$downloadLength-")
                .url(downloadUrl)
                .build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                iis = response.body!!.byteStream()
                saveFile = RandomAccessFile(file, "rw")
                saveFile.seek(downloadLength)
                var bytes = ByteArray(1024)
                var total = 0L
                var len: Int
                while ((iis.read(bytes).also { len = it }) != -1) {
                    when {
                        isCanceled -> {
                            return TYPE_CANCELED
                        }
                        isPause -> {
                            return TYPE_PAUSE
                        }
                        else -> {
                            total += len
                            saveFile.write(bytes, 0, len)
                            // 计算以下载百分比
                            val process = (((total + downloadLength).toDouble() / contentLength.toDouble()) * 100L).toInt()
                            publishProgress(process)
                        }
                    }
                }
                response.body!!.close()
                return TYPE_SUCCESS
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                iis?.close()
                saveFile?.close()
                if (isCanceled && file != null) {
                    file.delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return TYPE_FAILED
    }

    override fun onProgressUpdate(vararg values: Int?) {
        val progress = values[0]!!
        if (progress > lastProgress) {
            downloadListener.onProgress(progress)
            lastProgress = progress
        }
    }

    override fun onPostExecute(result: Int?) {
        when (result) {
            TYPE_SUCCESS -> downloadListener?.onSuccess()
            TYPE_FAILED -> downloadListener?.onFailed()
            TYPE_PAUSE -> downloadListener?.onPaused()
            TYPE_CANCELED -> downloadListener?.onCanceled()
        }
    }

    fun pauseDownload() {
        isPause = true
    }

    fun cancelDowload() {
        isCanceled = true
    }

    private fun getContentLength(downloadUrl: String): Long {
        val request = Request.Builder()
            .url(downloadUrl)
            .build()
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful) {
            val contentLength = response.body!!.contentLength()
            response.body?.close()
            return contentLength
        }
        return 0
    }

}