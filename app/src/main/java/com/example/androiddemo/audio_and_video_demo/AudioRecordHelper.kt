package com.example.androiddemo.audio_and_video_demo

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class AudioRecordHelper {

    private var bufferSize: Int = AudioRecord.getMinBufferSize(
        8000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )

    private var mRecord: AudioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        8000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_8BIT,
        bufferSize * 2
    )
    private var dos: DataOutputStream? = null
    private var recordThread: Thread? = null
    private var isStart = false


    companion object {
        // 单例
        val instance: AudioRecordHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioRecordHelper()
        }
    }

    private fun destroyThread() {
        try {
            isStart = false
            if (recordThread != null && Thread.State.RUNNABLE == recordThread!!.state) {
                try {
                    Thread.sleep(5000)
                    recordThread?.interrupt()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } finally {
            recordThread = null
        }
    }

    private fun startThread() {
        destroyThread()
        isStart = true
        if (recordThread == null) {
            recordThread = Thread(recordRunnable)
            recordThread?.start()
        }
    }

    val recordRunnable = Runnable {
        try {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO)
            var bytesRecord: Int
            val tempBuffer: ByteArray = ByteArray(bufferSize)
            if (mRecord.state != AudioRecord.STATE_INITIALIZED) {
                stopRecord()
                return@Runnable
            }
            mRecord.startRecording()
            while (isStart) {
                bytesRecord = mRecord.read(tempBuffer, 0, bufferSize)
                if (bytesRecord == AudioRecord.ERROR_INVALID_OPERATION || bytesRecord == AudioRecord.ERROR_BAD_VALUE) {
                    continue
                }
                if (bytesRecord != 0 && bytesRecord != -1) {
                    dos?.write(tempBuffer, 0, bytesRecord)
                } else {
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setPath(path: String) {
        val file = File(path)
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        dos = DataOutputStream(FileOutputStream(file, true))
    }

    fun startRecord(path: String) {
        setPath(path)
        startThread()
    }

    fun stopRecord() {
        try {
            destroyThread()
            if (mRecord.state == AudioRecord.STATE_INITIALIZED) {
                mRecord.stop()
            }
            mRecord.release()
            if (dos != null) {
                dos?.flush()
                dos?.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}