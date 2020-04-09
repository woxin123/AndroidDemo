package online.mengchen.androiddemo.ui_demo.view_pager_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import online.mengchen.androiddemo.R
import com.google.android.material.tabs.TabLayout

class ViewPagerAndTableLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_and_table_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager_view_pager2)
        val tabLayout: TabLayout = findViewById(R.id.view_pager_tab_layout)
        viewPager.adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return HelloFragment()
            }

            override fun getCount(): Int {
                return 4
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return "Hello $position"
            }
        }
        tabLayout.setupWithViewPager(viewPager)
    }
}
