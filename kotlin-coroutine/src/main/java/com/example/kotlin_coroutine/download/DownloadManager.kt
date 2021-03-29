package com.example.kotlin_coroutine.download

import com.example.kotlin_coroutine.api.HttpException
import com.example.kotlin_coroutine.api.okHttpClient
import com.example.kotlin_coroutine.appContext
import com.example.kotlin_coroutine.utils.copyTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import okhttp3.Request
import timber.log.Timber
import java.io.File

object DownloadManager {

    private val downloadDirectory by lazy {
        File(appContext.filesDir, "download").also { it.mkdirs() }
    }

    fun download(url: String, fileName: String): Flow<DownloadStatus> {
        val file = File(downloadDirectory, fileName)
        return flow {
            val request = Request.Builder().get().url(url).build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body!!.let { body ->
                    val total = body.contentLength()
                    val input = body.byteStream()
                    file.outputStream().use { output ->
                        var emittedProgress = 0L
                        input.copyTo(output) { bytesCopied ->
                            val progress = bytesCopied * 100 / total
                            if (progress - emittedProgress > 5) {
                                Timber.d("new progress: $progress")
                                emit(DownloadStatus.Progress(progress.toInt()))
                                emittedProgress = progress
                            }
                        }
                    }
                    input.close()
                }
                emit(DownloadStatus.Done(file))
            } else {
                throw HttpException(response)
            }
        }.catch {
            file.delete()
            emit(DownloadStatus.Error(it))
        }.conflate()
    }

}