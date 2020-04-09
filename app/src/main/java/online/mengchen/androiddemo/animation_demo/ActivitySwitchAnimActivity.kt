package online.mengchen.androiddemo.animation_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.R

class ActivitySwitchAnimActivity : AppCompatActivity(), View.OnClickListener {

    private val fadeInOut: Button by lazy { findViewById<Button>(R.id.btn_activity_switch_fade_in_out) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_anim)
        fadeInOut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_activity_switch_fade_in_out -> {
                val intent = Intent(this, ActivitySwitchFadeInOutActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}
