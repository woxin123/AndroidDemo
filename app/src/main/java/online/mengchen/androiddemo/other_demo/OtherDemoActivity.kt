package online.mengchen.androiddemo.other_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.architecture_demo.glide_demo.GlideDemoActivity
import kotlinx.android.synthetic.main.activity_other_demo.*

class OtherDemoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_demo)
        btnGlide.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGlide -> GlideDemoActivity::class.java
            else -> null
        }.let { startActivity(Intent(this, it)) }
    }
}
