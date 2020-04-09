package online.mengchen.androiddemo.animation_demo

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import online.mengchen.androiddemo.R

class SimpleAnimationScaleActivity: AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_animation_scale)
        imageView = findViewById(R.id.simple_animation_scale_image)
        // 代码实现
//        val scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1.1f, 0.6f)
//        scaleXAnimator.repeatCount = ValueAnimator.INFINITE
//        scaleXAnimator.repeatMode = ValueAnimator.REVERSE
//        scaleXAnimator.interpolator = LinearInterpolator()
//        scaleXAnimator.duration = 1000
//
//        val scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1.1f, 0.6f)
//        scaleYAnimator.repeatCount = ValueAnimator.INFINITE
//        scaleYAnimator.repeatMode = ValueAnimator.REVERSE
//        scaleYAnimator.interpolator = LinearInterpolator()
//        scaleYAnimator.duration = 1000
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
//        animatorSet.start()

        // xml 实现
        val animator = AnimatorInflater.loadAnimator(this, R.animator.scale_animator)
        animator.setTarget(imageView)
        animator.start()

    }
}