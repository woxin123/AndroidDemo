package com.example.androiddemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.PhoneAccount
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androiddemo.activity_demo.ActivityDemoActivity
import com.example.androiddemo.animation_demo.AnimationMenuActivity
import com.example.androiddemo.architecture_demo.ArchitectureMenuActivity
import com.example.androiddemo.audio_and_video_demo.AudioAndVideoMenuActivity
import com.example.androiddemo.customer_view_demo.CustomViewMenuActivity
import com.example.androiddemo.handler_demo.HandlerDemoMenuActivity
import com.example.androiddemo.ipc_demo.IPCDemoActivity
import com.example.androiddemo.jetpack_demo.JetPackDemoActivity
import com.example.androiddemo.network_demo.NetworkDemoMenuActivity
import com.example.androiddemo.other_demo.OtherDemoActivity
import com.example.androiddemo.phone_about.PhoneAboutSetting
import com.example.androiddemo.recyclerview_demo.SimpleRecyclerViewActivity
import com.example.androiddemo.service_demo.MyServiceActivity
import com.example.androiddemo.storage_demo.StorageDemoMenuActivity
import com.example.androiddemo.test_demo.TestDemoActivity
import com.example.androiddemo.ui_demo.UIActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * @BindView fields must not be private ro static
     */
    @BindView(R.id.btn_activity)       lateinit var activity: Button
    @BindView(R.id.btn_recyclerView)   lateinit var recyclerView: Button
    @BindView(R.id.btn_animation)      lateinit var animation: Button
    @BindView(R.id.btn_ui)             lateinit var ui: Button
    @BindView(R.id.btn_handler)        lateinit var handler: Button
    @BindView(R.id.btn_network)        lateinit var netWork: Button
    @BindView(R.id.btn_storage)        lateinit var storage: Button
    @BindView(R.id.btn_audio_and_video)lateinit var audioAndVideo: Button
    @BindView(R.id.btn_service)        lateinit var service: Button
    @BindView(R.id.btn_architecture)   lateinit var architecture: Button
    @BindView(R.id.btn_test)           lateinit var test: Button
    @BindView(R.id.btn_other_demo)     lateinit var other: Button
    @BindView(R.id.btn_jet_pack_demo)  lateinit var jetPack: Button
    @BindView(R.id.toolbar)            lateinit var toolbar: Toolbar
    @BindView(R.id.btn_ipc)            lateinit var ipc: Button
    @BindView(R.id.btn_customer)       lateinit var customer: Button

    private lateinit var drawerLayout: MyDrawerLayout

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        recyclerView.setOnClickListener(this)
        animation.setOnClickListener(this)
        ui.setOnClickListener(this)
        handler.setOnClickListener(this)
        netWork.setOnClickListener(this)
        storage.setOnClickListener(this)
        audioAndVideo.setOnClickListener(this)
        service.setOnClickListener(this)
        architecture.setOnClickListener(this)
        test.setOnClickListener(this)
        activity.setOnClickListener(this)
        jetPack.setOnClickListener(this)
        ipc.setOnClickListener(this)
        customer.setOnClickListener(this)
        other.setOnClickListener(this)
        drawerLayout = findViewById(R.id.my_drawer_layout)
        val drawerArrowDrawable = DrawerArrowDrawable(this)
        toolbar.navigationIcon = drawerArrowDrawable
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.START)
        }

        PhoneAboutSetting.setting(phone_about, this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_activity -> Intent(this, ActivityDemoActivity::class.java)
            R.id.btn_recyclerView -> {
                Intent(this, SimpleRecyclerViewActivity::class.java)
            }
            R.id.btn_animation -> {
                Intent(this, AnimationMenuActivity::class.java)
            }
            R.id.btn_ui -> {
                Intent(this, UIActivity::class.java)
            }
            R.id.btn_handler -> {
                Intent(this, HandlerDemoMenuActivity::class.java)
            }
            R.id.btn_network -> Intent(this, NetworkDemoMenuActivity::class.java)
            R.id.btn_storage -> Intent(this, StorageDemoMenuActivity::class.java)
            R.id.btn_audio_and_video -> Intent(this, AudioAndVideoMenuActivity::class.java)
            R.id.btn_service -> Intent(this, MyServiceActivity::class.java)
            R.id.btn_architecture -> Intent(this, ArchitectureMenuActivity::class.java)
            R.id.btn_test -> Intent(this, TestDemoActivity::class.java)
            R.id.btn_jet_pack_demo -> Intent(this, JetPackDemoActivity::class.java)
            R.id.btn_ipc -> Intent(this, IPCDemoActivity::class.java)
            R.id.btn_customer -> Intent(this, CustomViewMenuActivity::class.java)
            R.id.btn_other_demo -> Intent(this, OtherDemoActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }

//    override fun onBackPressed() {
//        finish()
//    }

    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
                drawerLayout.openDrawer(Gravity.START)
        return super.onOptionsItemSelected(item)
    }
}
