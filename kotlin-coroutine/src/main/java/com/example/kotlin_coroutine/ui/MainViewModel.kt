package com.example.kotlin_coroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_coroutine.download.DownloadManagerRx
import com.example.kotlin_coroutine.download.DownloadStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : ViewModel() {

    val downloadStatusLiveData = MutableLiveData<DownloadStatus>(DownloadStatus.None)

    fun download(url: String, fileName: String) {
        downloadRx(url, fileName)
    }

    private fun downloadRx(url: String, fileName: String) {
        Timber.d("use RxJava")
        DownloadManagerRx.download(url, fileName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                downloadStatusLiveData.value = it
            }
    }
}