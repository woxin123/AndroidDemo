package com.example.androiddemo.audio_and_video_demo.take_photo

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.hardware.Camera
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.util.SparseIntArray
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_take_photo.view.*
import kotlinx.android.synthetic.main.activity_ui.*
import java.util.*
import java.util.concurrent.Semaphore

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class CameraModule(val activity: AppCompatActivity, surfaceView: SurfaceView) {
    companion object {
        const val TAG = "CameraModule"

        /**
         * 相机状态：显示相机预览。
         */
        const val STATE_PREVIEW = 0

        /**
         * 相机状态：等待焦点被锁定。
         */
        const val STATE_WAITING_LOCK = 1

        /**
         * 等待曝光被Precapture状态。
         */
        const val STATE_WAITING_PRECAPTURE = 2

        /**
         * 相机状态：等待曝光的状态是不是Precapture。
         */
        const val STATE_WAITING_NON_PRECAPTURE = 3

        /**
         * 相机状态：拍照
         */
        const val STATE_PICTURE_TAKE = 4

        val ORIENTATIONS = SparseIntArray()

        // 为了使照片竖直显示
        init {
            ORIENTATIONS.append(Surface.ROTATION_0, 90)
            ORIENTATIONS.append(Surface.ROTATION_90, 0)
            ORIENTATIONS.append(Surface.ROTATION_180, 270)
            ORIENTATIONS.append(Surface.ROTATION_270, 180)
        }
    }

    private val manager: CameraManager =
        activity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private var mFlashSupport: Boolean = false
    private var mCameraId: String = ""
    private var mCameraDevice: CameraDevice? = null
    private var mPreviewRequestBuilder: CaptureRequest.Builder? = null
    private var mCaptureSession: CameraCaptureSession? = null
    private var mPreviewRequest: CaptureRequest? = null
    private var mBackgroundHandler: Handler? = null

    private lateinit var mImageReader: ImageReader
    private var imageView: ImageView


    private val mCameraOpenCloseLock = Semaphore(1)


    private val mHolder: SurfaceHolder

    init {
        for (cameraId in manager.cameraIdList) {
            // 获取相机相关参数
            val characteristics = manager.getCameraCharacteristics(cameraId)
            // 不使用前置摄像头
            Log.d(TAG, "cameraId = $cameraId")
            val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
            if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                continue
            }
            val available: Boolean? =
                characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            mFlashSupport = available != null
            mCameraId = cameraId
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

        }
        imageView = activity.findViewById(R.id.show_picture)
        imageView.visibility = View.GONE
        mHolder = surfaceView.holder
        surfaceView.keepScreenOn = true

        mHolder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                initCamera()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                mCameraDevice?.close()
                mCameraDevice = null
            }
        })
    }

    fun initCamera() {
        val handlerThread = HandlerThread("Camera")
        handlerThread.start()
        mBackgroundHandler = Handler(handlerThread.looper)
        val mainHandler = Handler(Looper.getMainLooper())
        mImageReader = ImageReader.newInstance(1080, 1920, ImageFormat.JPEG, 1)
        mImageReader.setOnImageAvailableListener({ reader: ImageReader ->
            // 拿到的拍照数据
            val image = reader.acquireNextImage()
            val buffer = image.planes[0].buffer
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                imageView.visibility = View.VISIBLE
            }
        }, mainHandler)
        openCamera()
    }


    @SuppressLint("MissingPermission")
    fun openCamera() {
        manager.openCamera(mCameraId, object : CameraDevice.StateCallback() {

            @RequiresApi(Build.VERSION_CODES.P)
            override fun onOpened(camera: CameraDevice) {
                mCameraOpenCloseLock.release()
                mCameraDevice = camera
                // 创建 CameraPreviewSession
                createCameraPreviewSession()
            }


            override fun onDisconnected(camera: CameraDevice) {
            }

            override fun onError(camera: CameraDevice, error: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }, mBackgroundHandler!!)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun createCameraPreviewSession() {
        mPreviewRequestBuilder = mCameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        mPreviewRequestBuilder?.addTarget(mHolder.surface)
        // 创建一个 CameraCaptureSession 来进行相机预览
        mCameraDevice?.createCaptureSession(
            listOf(mHolder.surface, mImageReader.surface),
            object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    // 相机已关闭
                    if (mCameraDevice == null) {
                        return
                    }

                    // 回话准备好，开始显示预览
                    mCaptureSession = session
                    mPreviewRequestBuilder?.set(
                        CaptureRequest.CONTROL_AF_MODE,
                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
                    )
                    // 闪光灯
                    mPreviewRequestBuilder?.set(
                        CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH
                    )
                    // 开启相机并预览并添加事件
                    mPreviewRequest = mPreviewRequestBuilder?.build()
                    // 发送请求
                    mCaptureSession?.setRepeatingRequest(
                        mPreviewRequest!!,
                        null,
                        mBackgroundHandler
                    )
                    Log.e(TAG, "开启相机预览并添加事件")
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {
                    Log.e(TAG, "onConfigureFailed 开启预览失败")
                }
            }, null
        )

    }


    fun takePhoto() {
        if (mCameraDevice != null) {
            val captureRequestBuilder =
                mCameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
            // 将 SurfaceVIew 的 surface 作为 CaptureRequest.Builder 的目标
            captureRequestBuilder.addTarget(mImageReader.surface)
            // 自动对焦
            captureRequestBuilder.set(
                CaptureRequest.CONTROL_AF_MODE,
                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
            )
            // 自动曝光
            captureRequestBuilder.set(
                CaptureRequest.CONTROL_AE_MODE,
                CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH
            )
            // 获取手机方向
            val rotation = activity.windowManager.defaultDisplay.rotation
            // 根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation))
            // 拍照
            val mCaptureRequest = captureRequestBuilder.build()
            mCaptureSession?.capture(mCaptureRequest, null, mBackgroundHandler)
        }
    }

    fun closeCamera() {

    }

}