package online.mengchen.androiddemo.audio_and_video_demo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import online.mengchen.androiddemo.R
import java.io.File

class VideoPlayActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        videoView = findViewById(R.id.audio_and_video_video_play_video_view)
        val play: Button = findViewById(R.id.btn_audio_and_video_video_play_play)
        val pause: Button = findViewById(R.id.btn_audio_and_video_video_play_pause)
        val replay: Button = findViewById(R.id.btn_audio_and_video_video_play_replay)
        play.setOnClickListener(this)
        pause.setOnClickListener(this)
        replay.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            initVideoPath()
        }
    }

    private fun initVideoPath() {
        val file = File(this.filesDir, "Movies/video.mp4")
        Log.d("aaa", file.absolutePath)
        videoView.setVideoPath(file.path)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath()
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_audio_and_video_video_play_play -> {
                if (!videoView.isPlaying) {
                    videoView.start()
                }
            }
            R.id.btn_audio_and_video_video_play_pause -> {
                if (videoView.isPlaying) {
                    videoView.pause()
                }
            }
            R.id.btn_audio_and_video_video_play_replay -> {
                if (videoView.isPlaying) {
                    videoView.resume()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}
