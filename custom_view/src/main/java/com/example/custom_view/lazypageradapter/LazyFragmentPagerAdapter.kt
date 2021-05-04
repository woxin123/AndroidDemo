package com.example.custom_view.lazypageradapter

import android.view.View
import android.view.ViewGroup
import androidx.core.util.set
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * @Author mengchen
 * @DateTime 2021/4/30 11:08 AM
 */
abstract class LazyFragmentPagerAdapter(
    private val mFragmentManager: FragmentManager
) : LazyPagerAdapter<Fragment>() {
    
    companion object {
        private const val TAG = "LazyFragmentPagerAdapter"
        private fun makeFragmentName(viewId: Int, id: Long): String {
            return "android:switcher:" + viewId + ":" + id
        }
    }
    
    interface Laziable {
    }
    
    private var mCurTransaction: FragmentTransaction? = null

    override fun startUpdate(container: ViewGroup) {
        
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction()
        }
        // Do we already have this fragment?
        val name = makeFragmentName(container.id, position.toLong())
        var fragment = mFragmentManager.findFragmentByTag(name)
        if (fragment != null) {
            mCurTransaction?.attach(fragment)
        } else {
            fragment = getItem(container, position)
            if (fragment is Laziable) {
                mLazyItems[position] = fragment
            } else {
                mCurTransaction?.add(container.id, fragment, name)
            }
        }
        if (fragment != getCurrentItem()) {
            fragment.setMenuVisibility(false)
            fragment.userVisibleHint = false
        }
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction()
        }
        val name = makeFragmentName(container.id, position.toLong())
        if (mFragmentManager.findFragmentByTag(name) == null) {
            mCurTransaction?.detach(`object` as Fragment)
        } else {
            mLazyItems.remove(position)
        }
    }

    override fun addLazyItem(container: ViewGroup, position: Int): Fragment? {
        val fragment = mLazyItems.get(position)
        if (fragment != null) {
            val name = makeFragmentName(container.id, position.toLong())
            if (mFragmentManager.findFragmentByTag(name) == null) {
                mCurTransaction = mFragmentManager.beginTransaction()
            }
            mCurTransaction?.add(container.id, fragment, name)
            mLazyItems.remove(position)
            return fragment
        }
        return null
    }

    override fun finishUpdate(container: ViewGroup) {
        mCurTransaction?.let { it ->
            it.commitAllowingStateLoss()
            mCurTransaction = null
            mFragmentManager.executePendingTransactions()
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as Fragment).view == view
    }
    
}