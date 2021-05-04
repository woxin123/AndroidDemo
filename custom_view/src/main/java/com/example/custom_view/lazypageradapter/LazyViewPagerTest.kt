package com.example.custom_view.lazypageradapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.custom_view.R
import com.example.custom_view.common.CustomView
import com.example.common_utils.getActivity
import com.example.custom_view.lazypageradapter.LazyFragmentPagerAdapter.Laziable

/**
 * @Author mengchen
 * @DateTime 2021/5/4 4:11 PM
 */
class LazyViewPagerTest : CustomView {
    
    private val mItem = Array(10) { index ->
        MyFragment(('A' + index).toString())
    }
    
    override val customViewName: String
        get() = "LazyViewPager"
    override val customViewLayout: Int
        get() = R.layout.custom_view_lazy_view_pager

    override fun initCustomView(view: View, activity: Activity) {
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        activity.let {
            viewPager.adapter = MyLazyViewPagerAdapter((it as FragmentActivity).supportFragmentManager)
        }
    }
    
    inner class MyLazyViewPagerAdapter(fragmentManager: FragmentManager): LazyFragmentPagerAdapter(fragmentManager) {
        override fun getItem(container: ViewGroup, position: Int): Fragment {
            return mItem[position]
        }

        override fun getCount(): Int {
            return mItem.size
        }

    }
    
    class MyFragment(private val message: String) : Fragment(), Laziable {
        
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(android.R.layout.simple_list_item_1, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            view.findViewById<TextView>(android.R.id.text1).text = message
        }
    }

}