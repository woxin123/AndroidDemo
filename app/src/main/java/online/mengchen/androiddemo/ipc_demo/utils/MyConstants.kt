package online.mengchen.androiddemo.ipc_demo.utils

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi

object MyConstants {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    val PATH:String = Environment.getExternalStorageDirectory().path + "/" + Environment.DIRECTORY_DOCUMENTS
    val CACHE_FILE_PATH = "$PATH/usercache"
    const val MSG_FROM_CLIENT = 0
    const val MSG_FROM_SERVER = 1
}