package com.example.androiddemo.architecture_demo.glide_demo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bumptech.glide.Glide
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_glide_demo.*
import java.io.File

class GlideDemoActivity : AppCompatActivity() {

    companion object {
        const val PATH = "/DCIM/表情包/E5FC69CDE941A4E6479BC2B64D03AF90.jpg"
        const val RESOURCE = "android.resource://"
        const val SLASH = "/"
        const val RESID = R.mipmap.ic_launcher
        const val URL_IMG = "http://p4.so.qhimg.com/t010c102c7b029340d4.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_demo)
        Glide.with(this).load(R.mipmap.ic_launcher).into(imageView)
        val file = File(Environment.getExternalStorageDirectory().path + PATH)
        Log.d("mengchen", file.absolutePath)
        Glide.with(this).load(file).into(imageViewFromFile)
        val uri = Uri.parse(RESOURCE + packageName + SLASH + RESID)
        Glide.with(this).load(uri).into(imageViewFromUri)
        Glide.with(this).load(URL_IMG).into(imageViewFromNetWork)
    }
}
