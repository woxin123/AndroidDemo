package online.mengchen.androiddemo.jetpack_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.mengchen.androiddemo.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        // 获取该 Activity 的 LifeCycle 并 添加一个观察者
        lifecycle.addObserver(MyObserver())
    }
}
