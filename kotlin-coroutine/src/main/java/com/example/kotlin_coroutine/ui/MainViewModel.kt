package com.example.kotlin_coroutine.ui

import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.concurrent.futures.DirectExecutor
import androidx.concurrent.futures.await
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutine.api.GitUser
import com.example.kotlin_coroutine.api.gitHubServiceApi
import com.example.kotlin_coroutine.db.Db
import com.example.kotlin_coroutine.db.User
import com.example.kotlin_coroutine.download.DownloadManager
import com.example.kotlin_coroutine.download.DownloadManagerRx
import com.example.kotlin_coroutine.download.DownloadStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.lang.NullPointerException

class MainViewModel : ViewModel() {

    val downloadStatusLiveData = MutableLiveData<DownloadStatus>(DownloadStatus.None)

    val gitUserLiveData = MutableLiveData<GitUser>()

    val userLiveData = MutableLiveData<List<User>>()

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

    private fun downloadRx2Coroutine(url: String, fileName: String) {
        DownloadManagerRx.download(url, fileName)
            .subscribeOn(Schedulers.io())
            .asFlow()
            .onEach {
                Timber.d("onEach: $it")
            }
            .launchIn(viewModelScope)
    }

    suspend fun loadGitUsers() {
        gitUserLiveData.value = CallbackToFutureAdapter.getFuture<GitUser> { completer ->
            val call = gitHubServiceApi.getUserCallback("woxin123")
            completer.addCancellationListener(Runnable {
                call.cancel()
            }, DirectExecutor.INSTANCE)
            call.enqueue(object : Callback<GitUser> {
                override fun onFailure(call: Call<GitUser>, t: Throwable) {
                    completer.setException(t)
                }

                override fun onResponse(call: Call<GitUser>, response: Response<GitUser>) {
                    if (!response.isSuccessful) {
                        completer.setException(HttpException(response))
                    } else {
                        response.body()?.let(completer::set)
                            ?: completer.setException(NullPointerException())
                    }
                }
            })
        }.await()

    }

    suspend fun loadUsers() {
        userLiveData.value = Db.userDao.listUsers()
    }
}