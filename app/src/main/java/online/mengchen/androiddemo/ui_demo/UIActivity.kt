package online.mengchen.androiddemo.ui_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.ui_demo.surface_demo.SurfaceViewActivity
import online.mengchen.androiddemo.ui_demo.view_pager_demo.ViewPagerActivity
import online.mengchen.androiddemo.ui_demo.view_pager_demo.ViewPagerAndTableLayoutActivity

class UIActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var viewPager: Button
    private lateinit var viewPagerAndTableLayout: Button
    private lateinit var surface: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)
        viewPager = findViewById(R.id.btn_view_pager)
        viewPager.setOnClickListener(this)
        viewPagerAndTableLayout = findViewById(R.id.btn_view_pager_and_table_layout)
        viewPagerAndTableLayout.setOnClickListener(this)
        surface = findViewById(R.id.btn_surface)
        surface.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_view_pager -> {
                val intent = Intent(this, ViewPagerActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_view_pager_and_table_layout -> {
                val intent = Intent(this, ViewPagerAndTableLayoutActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_surface -> {
                startActivity(Intent(this, SurfaceViewActivity::class.java))
            }
        }
    }
}