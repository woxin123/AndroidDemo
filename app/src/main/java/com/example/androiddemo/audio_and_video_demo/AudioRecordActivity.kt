package com.example.androiddemo.audio_and_video_demo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.androiddemo.R

class AudioRecordActivity : AppCompatActivity() {

    private lateinit var start: Button
    private lateinit var stop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_record)
        start = findViewById(R.id.btn_audio_and_video_audio_record_start)
        stop = findViewById(R.id.btn_audio_and_video_audio_record_stop)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }
        val audioRecordHelper = AudioRecordHelper.instance
        start.setOnClickListener {
            audioRecordHelper.startRecord(this.filesDir.absolutePath + "/record.mp3")
        }
        stop.setOnClickListener {
            audioRecordHelper.stopRecord()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "You denied permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
