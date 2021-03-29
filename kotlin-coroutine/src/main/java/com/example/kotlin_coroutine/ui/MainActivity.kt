package com.example.kotlin_coroutine.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import com.example.kotlin_coroutine.R
import com.example.kotlin_coroutine.api.GitUser
import com.example.kotlin_coroutine.db.User
import com.example.kotlin_coroutine.download.DownloadStatus
import com.example.kotlin_coroutine.legacy.ImageAsyncTask
import com.example.kotlin_coroutine.legacy.ImageAsyncTaskWithCallback
import com.example.kotlin_coroutine.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.launch
import splitties.alertdialog.appcompat.alert
import splitties.alertdialog.appcompat.alertDialog
import splitties.alertdialog.appcompat.coroutines.showAndAwait
import splitties.alertdialog.appcompat.message

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val listAdapter by lazy {
        ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)
    }

    private val imageLoader by lazy {
        ImageLoader(this) {
            componentRegistry {
                add(SvgDecoder(this@MainActivity))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDialogButton.setOnClickListener {
            lifecycleScope.launch {
                val myChoice = alertDialog("Warning!", "Do you want this?")
                toast("My Choice is $myChoice")

                val result = alertDialog {
                    message = "Dialog from Sqlitties"
                }.showAndAwait(
                    okValue = 1,
                    cancelValue = 0,
                    dismissValue = 1
                )
                toast("Result from sqlitties dialog: $result")
            }
        }

        mainViewModel.downloadStatusLiveData.observe(this) { downloadStatus ->
            when (downloadStatus) {
                null, DownloadStatus.None -> {
                    downLoadButton.text = "Download"
                    downLoadButton.setOnClickListener {
                        mainViewModel.download(
                            "https://kotlinlang.org/docs/kotlin-reference.pdf",
                            "Kotlin-Docs.pdf"
                        )
                    }
                }
                is DownloadStatus.Progress -> {
                    downLoadButton.isEnabled = false
                    downLoadButton.text = "Download (${downloadStatus.value})"
                }
                is DownloadStatus.Error -> {
                    toast(downloadStatus.throwable)
                    downLoadButton.text = "Download Error"
                    downLoadButton.isEnabled = true
                    downLoadButton.setOnClickListener {
                        mainViewModel.download(
                            "https://kotlinlang.org/docs/kotlin-reference.pdf",
                            "Kotlin-Docs.pdf"
                        )
                    }
                }
                is DownloadStatus.Done -> {
                    toast("Done: ${downloadStatus.file.name}")
                    downLoadButton.isEnabled = true
                    downLoadButton.text = "Open File"
                    downLoadButton.setOnClickListener {
                        Intent(Intent.ACTION_VIEW).also {
                            it.setDataAndType(
                                FileProvider.getUriForFile(
                                    this, "${packageName}.provider",
                                    downloadStatus.file
                                ), "application/pdf"
                            )
                            it.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
                        }.also(::startActivity)
                    }
                }
            }
        }

        userListView.adapter = listAdapter
        mainViewModel.userLiveData.observe(this) { users ->
            listAdapter.clear()
            users?.let {
                userListView.visibility = View.VISIBLE
                listAdapter.addAll(it)
            } ?: run {
                userListView.visibility = View.GONE
            }

        }

        loadUserFromDb.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.loadUsers()
            }
        }

        mainViewModel.gitUserLiveData.observe(this) { gitUser: GitUser? ->
            gitUserTextView.text = gitUser.toString()
        }

        loadGitUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.loadGitUsers()
            }
        }

        loadImageButton.setOnClickListener {
            lifecycleScope.launch {
                logoView.load(
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3950890774,2925186961&fm=26&gp=0.jpg",
                    imageLoader
                ).await()
            }
        }

        loadImage2Button.setOnClickListener {
            ImageAsyncTaskWithCallback(onComplete = { bitmap ->
                logoView.setImageBitmap(bitmap)
            }).execute("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1257261576,679557898&fm=26&gp=0.jpg")
        }
    }
}