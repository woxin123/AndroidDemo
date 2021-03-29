package com.example.kotlin_coroutine.download

import com.example.kotlin_coroutine.api.HttpException
import com.example.kotlin_coroutine.api.okHttpClient
import com.example.kotlin_coroutine.appContext
import com.example.kotlin_coroutine.utils.copyTo
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import okhttp3.Request
import timber.log.Timber
import java.io.File

object DownloadManagerRx {

    private val downloadDirectory by lazy {
        File(appContext.filesDir, "download").also { it.mkdirs() }
    }

    fun download(url: String, fileName: String): Flowable<DownloadStatus> {
        val file = File(downloadDirectory, fileName)
        return Flowable.create<DownloadStatus>({
            val request = Request.Builder().url(url).get().build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body!!.let { body ->
                    val total = body.contentLength()
                    file.outputStream().use { output ->
                        val input = body.byteStream()
                        var emitterProgress = 0L
                        input.copyTo(output) { bytesCopied ->
                            Timber.d("bytesCopied = ${bytesCopied}")
                            val progress = bytesCopied * 100 / total
                            if (progress - emitterProgress > 5) {
                                Timber.d("new progress: $progress")
                                Thread.sleep(1000)
                                it.onNext(DownloadStatus.Progress(progress.toInt()))
                                emitterProgress = progress
                            }
                        }
                        input.close()
                    }
                    it.onNext(DownloadStatus.Done(file))
                }
            } else {
                throw HttpException(response)
            }
            it.onComplete()
        }, BackpressureStrategy.BUFFER).onErrorReturn {
            file.delete()
            DownloadStatus.Error(it)
        }
    }

}