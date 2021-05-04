package com.example.custom_view.lazypageradapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter

/**
 * @Author mengchen
 * @DateTime 2021/4/29 8:51 PM
 */
abstract class LazyPagerAdapter<T> : PagerAdapter() {
    
    protected val mLazyItems = SparseArray<T>()
    private var mCurrentItem: T? = null
    
    protected abstract fun addLazyItem(container: ViewGroup, position: Int): T?
    
    protected abstract fun getItem(container: ViewGroup, position: Int): Fragment
    
    fun isLazyItem(position: Int): Boolean {
        return mLazyItems[position] != null
    }
    
    fun getCurrentItem(): T? {
        return mCurrentItem
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        mCurrentItem = addLazyItem(container, position)
    }
}