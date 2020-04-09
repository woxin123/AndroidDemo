package online.mengchen.androiddemo.test_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import online.mengchen.androiddemo.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusTestDemoActivity : AppCompatActivity() {

    private val button by lazy { findViewById<Button>(R.id.btn_test_event_bus_btn) }
    private val textView by lazy {findViewById<TextView>(R.id.btn_test_event_bus_text)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_test_demo)
        EventBus.getDefault().register(this)
        button.setOnClickListener {
            textView.append("Click\n")
            EventBus.getDefault().post(EventMessage("哈哈，你点击了这个按钮"))
        }
    }

    @Subscribe(threadMode =  ThreadMode.MAIN )
    fun onEventBusClick(message: EventMessage) {
        textView.append("$message\n")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
