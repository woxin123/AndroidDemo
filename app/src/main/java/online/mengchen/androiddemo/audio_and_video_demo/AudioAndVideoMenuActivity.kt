package online.mengchen.androiddemo.audio_and_video_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.audio_and_video_demo.take_photo.TakePhotoActivity

class AudioAndVideoMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var audioPlay: Button
    private lateinit var videoPlay: Button
    private lateinit var audioRecord: Button
    private lateinit var takePicture: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_and_video_menu)
        audioPlay = findViewById(R.id.btn_audio_and_video_audio_play)
        videoPlay = findViewById(R.id.btn_audio_and_video_video_play)
        audioRecord = findViewById(R.id.btn_audio_and_video_audio_record)
        takePicture = findViewById(R.id.btn_audio_and_video_take_picture)
        audioPlay.setOnClickListener(this)
        videoPlay.setOnClickListener(this)
        audioRecord.setOnClickListener(this)
        takePicture.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_audio_and_video_audio_play -> Intent(this, AudioPlayActivity::class.java)
            R.id.btn_audio_and_video_video_play -> Intent(this, VideoPlayActivity::class.java)
            R.id.btn_audio_and_video_audio_record -> Intent(this, AudioRecordActivity::class.java)
            R.id.btn_audio_and_video_take_picture -> Intent(this, TakePhotoActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }
}
