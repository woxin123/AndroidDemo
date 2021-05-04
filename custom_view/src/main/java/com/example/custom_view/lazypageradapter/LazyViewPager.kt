package com.example.custom_view.lazypageradapter

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.custom_view.R

/**
 * @Author mengchen
 * @DateTime 2021/4/28 4:13 PM
 */
class LazyViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    
    companion object {
        private const val DEFAULT_OFFSET = 0.5F
    }
    
    private var mLazyPagerAdapter: LazyPagerAdapter<Any>? = null
    
    private var mInitLazyItemOffset = DEFAULT_OFFSET
    
    init {
        context.obtainStyledAttributes(attrs, R.styleable.LazyViewPager).apply { 
            mInitLazyItemOffset = getFloat(R.styleable.LazyViewPager_init_lazy_item_offset, DEFAULT_OFFSET)
            recycle()
        }
    }
    
    fun setInitLazyItemOffset(initLazyItemOffset: Float) {
        this.mInitLazyItemOffset = initLazyItemOffset
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        
    }
    
}