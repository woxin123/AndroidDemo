package online.mengchen.androiddemo.network_demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import online.mengchen.androiddemo.R

@Suppress("NAME_SHADOWING")
class NetWorkHttpClientActivity : AppCompatActivity() {

    private val editText by lazy { findViewById<EditText>(R.id.net_work_http_client_edit_text) }
    private val get by lazy { findViewById<Button>(R.id.btn_net_work_http_client_get) }
    private val post by lazy { findViewById<Button>(R.id.btn_net_work_http_client_post) }
    private val result by lazy { findViewById<TextView>(R.id.net_work_http_client_result) }
    private lateinit var method: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work_http_client)
        method = intent.extras?.get("Method") as String
        editText.append("https://www.baidu.com")
        val handler = @SuppressLint("HandlerLeak")
        object: Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.arg1 == 1) {
                    result.text = msg.data.getString("result")
                }
                if (msg.what == 1) {
                    result.text = "Error"
                }
            }
        }

        get.setOnClickListener {
            val url = editText.text.toString()
            if (url.trim() == "") {
                Toast.makeText(this, "网址不能为空", Toast.LENGTH_SHORT).show()
            } else {
                val thread = Thread {
                    var result: String? = null
                    when (method) {
                        "HttpClient" -> {
                            result = HttpClientDemo().useHttpClientGet(url)
                        }
                        "HttpURLConnection" -> {
                            result = HttpURLConnectionDemo().useHttpURLConnectionGet(url)
                        }
                        "OkHttp" -> {
                            result = OkHttpDemo().useSyncOkHttp(url)
                        }
                    }
                    if (result == null) {
                        handler.sendEmptyMessage(1)
                    } else {
                        val message = handler.obtainMessage()
                        val bundle = Bundle()
                        bundle.putString("result", result)
                        message.data = bundle
                        message.arg1 = 1
                        handler.sendMessage(message)
                    }
                }
                thread.start()
            }
        }
    }
}
