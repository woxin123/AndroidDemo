package com.example.kotlin_coroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutine.download.DownloadManager
import com.example.kotlin_coroutine.download.DownloadManagerRx
import com.example.kotlin_coroutine.download.DownloadStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    val downloadStatusLiveData = MutableLiveData<DownloadStatus>(DownloadStatus.None)

    private val isUseCoroutine = true

    fun download(url: String, fileName: String) {
        if (!isUseCoroutine) {
            downloadRx(url, fileName)
        } else {
            viewModelScope.launch {
                downloadSuspend(url, fileName)
            }
        }
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

    private suspend fun downloadSuspend(url: String, fileName: String) {
        Timber.d("use flow")
        DownloadManager.download(url, fileName)
            .flowOn(Dispatchers.IO)
            .collect {
                downloadStatusLiveData.value = it
            }
    }
}