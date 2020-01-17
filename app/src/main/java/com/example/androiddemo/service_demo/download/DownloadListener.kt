package com.example.androiddemo.service_demo.download

interface DownloadListener {
    fun onProgress(progress: Int)
    fun onSuccess()
    fun onFailed()
    fun onPaused()
    fun onCanceled()
}