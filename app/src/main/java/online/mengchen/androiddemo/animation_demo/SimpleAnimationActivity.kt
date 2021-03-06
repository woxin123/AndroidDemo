package online.mengchen.androiddemo.animation_demo

import android.animation.AnimatorInflater
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import online.mengchen.androiddemo.R

class SimpleAnimationActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_animation)
        imageView = findViewById(R.id.simple_animation_image)
        // 利用代码编写动画
//        val animator: ObjectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0F, 360.0F)
//        animator.repeatCount = ValueAnimator.INFINITE
//        animator.repeatMode = ValueAnimator.RESTART
//        animator.interpolator = LinearInterpolator()
//        animator.duration = 8000
        // 利用 xml
        val animator = AnimatorInflater.loadAnimator(this, R.animator.simple_animator)
        animator.setTarget(imageView)
        animator.start()
    }
}
