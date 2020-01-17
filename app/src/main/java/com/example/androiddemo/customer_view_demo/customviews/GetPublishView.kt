package com.example.androiddemo.customer_view_demo.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.RelativeLayout
import com.example.androiddemo.customer_view_demo.common.DisplayUtils
import kotlinx.android.synthetic.main.custom_get_publish_view.view.*
import kotlin.math.sqrt

class GetPublishView : RelativeLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    // 是否已经弹出
    private var isPoped = false

    override fun onFinishInflate() {
        super.onFinishInflate()
        // 点击弹出
        publish.setOnClickListener {
            if (!isPoped) open() else close()
            isPoped = !isPoped
        }
    }

    private fun open() {
        reset()

        val animDuration = 300L
        publish.animate().rotation(-4.5F).setDuration(animDuration).start()
        val dex = DisplayUtils.dp2px(context, 80F * sqrt(3F) / 2F).toFloat()
        val dex2 = DisplayUtils.dp2px(context, 80F * 0.5F).toFloat()
        item1.animate().translationY((-DisplayUtils.dp2px(context, 80F)).toFloat())
            .setDuration(animDuration).setInterpolator(BounceInterpolator()).start()
        item2.animate().translationX(-dex).translationY(-dex2).setDuration(animDuration)
            .setInterpolator(BounceInterpolator()).setStartDelay(50).start()
        item3.animate().translationX(-dex).translationY(dex2).setDuration(animDuration)
            .setInterpolator(BounceInterpolator()).setStartDelay(100).start()
        item4.animate().translationY((-DisplayUtils.dp2px(context, -80F)).toFloat())
            .setDuration(animDuration).setInterpolator(BounceInterpolator()).setStartDelay(150)
            .start()
    }

    private fun close() {
        publish.animate().rotation(0F).setDuration(300).start()
        closeAnim(item1)
        closeAnim(item2)
        closeAnim(item3)
        closeAnim(item4)
    }

    private fun reset() {
        item1.rotation = 0F
        item2.rotation = 0F
        item3.rotation = 0F
        item4.rotation = 0F
    }

    private fun closeAnim(view: View) {
        view.animate().rotation(-360F * 5).setDuration(200).setInterpolator(null).setStartDelay(0)
            .start()
        view.animate().translationX(0F).translationY(0F).setInterpolator(null).setStartDelay(150).start()
    }
}