package online.mengchen.androiddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.Button

class SplashActivity : AppCompatActivity() {

    private val time = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )//设置全屏
        setContentView(R.layout.activity_splash)
        val button = findViewById<Button>(R.id.btn_timer)



        var countDownTimer = object: CountDownTimer(time, 1000) {
            private var t = time / 1000
            override fun onFinish() {
                    startMainActivity()
            }

            override fun onTick(millisUntilFinished: Long) {
                t--
                button.text = "跳过${t}s"
            }

        }.start()

        button.setOnClickListener {
            countDownTimer.cancel()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        if (!isFinishing) {
            finish()
        }
    }




}
