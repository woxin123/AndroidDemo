package online.mengchen.androiddemo.audio_and_video_demo.take_photo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import androidx.core.app.ActivityCompat
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.activity_take_photo.*

class TakePhotoActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView

    private lateinit var button: Button

    private lateinit var cameraModule: CameraModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        initView()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            initCamera()
        }
        button.setOnClickListener {
            cameraModule.takePhoto()
        }
    }


    private fun initView() {
        surfaceView = previewView
        button = takePhoto
    }

    private fun initCamera() {
        cameraModule = CameraModule(this, surfaceView)
//        cameraModule.initCamera()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               initCamera()
            }
        }
    }

}
