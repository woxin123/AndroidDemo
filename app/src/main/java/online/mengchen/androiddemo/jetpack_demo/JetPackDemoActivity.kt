package online.mengchen.androiddemo.jetpack_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.jetpack_demo.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_jet_pack_demo.*

class JetPackDemoActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "JetPackDemoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack_demo)
        btn_jet_pack_lifecycle.setOnClickListener(this)
        btn_navigation.setOnClickListener(this)
        btn_livedata.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_jet_pack_lifecycle -> Intent(this, OrdinaryActivityLifecycleActivity::class.java)
            R.id.btn_navigation -> Intent(this, NavigationActivity::class.java)
            R.id.btn_livedata -> Intent(this, LiveDataActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }
}
