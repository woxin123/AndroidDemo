package online.mengchen.androiddemo.customer_view_demo.customviews.mafengwo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.custom_view_mfw_wriggle_tab_view.view.*

class MFWWriggleTab: LinearLayout {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)


    private val tabList = listOf("正在旅行", "推荐", "附近", "视频", "春节", "国内", "国外", "网红打卡地", "取景地巡礼",
        "带娃旅行", "海岛游", "海岛游", "情侣出行", "自驾游")

    private var contentList: ArrayList<TextView>? = null

    private var titleList: ArrayList<TextView>? = null

    // 当前选中的位置
    private var currentPos = 0

    private val vpAdapter by lazy { VPAdapter() }

    override fun onFinishInflate() {
        super.onFinishInflate()

        // ScrollView 中的内容
        titleList = ArrayList()
        // ViewPager 中的内容
        contentList = ArrayList()

        for (tab in tabList) {
            val tabView = LayoutInflater.from(context).inflate(R.layout.custom_view_mfw_tab_title_item_layout,
                ll_tab, false) as TextView
            tabView.text = tab
            val tv = TextView(context)
            tv.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            tv.gravity = Gravity.CENTER
            tv.setTextColor(Color.RED)
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0f)
            contentList?.add(tv)
            titleList?.add(tabView)
            ll_tab.addView(tabView)
        }
        with(vp_content) {
            adapter = vpAdapter
            currentItem = 0
        }


        vp_content.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // 设置蠕动指示器百分比变化
                mfw_indicator.setPercentNum(positionOffset)
                // 慢慢移动到选中的 tab 和指示器并居中
                moveTabAndIndicatorToSelectedCenter(position, positionOffset)
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                // 修改选中 tab 的样式
                changeSelectedStyle(position)
            }
        })

        for (i in 0 until titleList!!.size) {
            val tv = titleList!![i]
            tv.setOnClickListener {
                moveTabAndIndicatorToSelectedCenter(i, 0.0F)
                vp_content.setCurrentItem(i, false)
            }
        }

        // 初始状态和位置
        vp_content.currentItem = 0
        // 方法第一个 tab 字体
        changeSelectedStyle(0)
        post {
            // 初始化指示器位置
            mfw_indicator.translationX = (titleList!![0].width / 2 - mfw_indicator.width / 2).toFloat()
        }
    }


    /**
     * 修改选中 tab 样式
     */
    private fun changeSelectedStyle(position: Int) {
        titleList?.forEach {
            it.scaleX = 1.0F
            it.scaleY = 1.0F
            it.setTextColor(Color.parseColor("#666666"))
        }
        with(titleList!![position]) {
            scaleX = 1.2F
            scaleY = 1.2F
            setTextColor(Color.parseColor("#000000"))
        }
    }

    /**
     * 慢慢移动到选中的 tab 和指示器并居中
     */
    private fun moveTabAndIndicatorToSelectedCenter(position: Int, positionOffset: Float) {
        val tv = titleList!![position]
        val tvCur = titleList!![currentPos]
        val dex = tv.width / 2 + tvCur.width / 2
        // 挪动 tab
        sv_tab.scrollTo(((tv.left - ((sv_tab.width - tv.width) / 2) + dex * positionOffset).toInt()), 0)
        // 挪动指示器
        mfw_indicator.translationX = tv.x + tv.width / 2 - mfw_indicator.width / 2 + dex * positionOffset
    }

    inner class VPAdapter: PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount() = tabList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val tv: TextView = contentList!![position]
            tv.text = tabList[position]
            container.addView(tv)
            return tv
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(contentList!![position])
        }
    }
}