package online.mengchen.androiddemo.network_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.activity_network_demo_menu.*

class NetworkDemoMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var httpClient: Button
    private lateinit var httpURLConnection: Button
    private lateinit var okHttp: Button
    private lateinit var asyncOkHttp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_demo_menu)
        httpClient = findViewById(R.id.btn_network_httpClient)
        httpURLConnection = findViewById(R.id.btn_network_http_url_connection)
        okHttp = findViewById(R.id.btn_network_okhttp)
        httpClient.setOnClickListener(this)
        httpURLConnection.setOnClickListener(this)
        okHttp = findViewById(R.id.btn_network_okhttp)
        okHttp.setOnClickListener(this)
        asyncOkHttp = findViewById(R.id.btn_network_async_okhttp)
        asyncOkHttp.setOnClickListener(this)
        btn_network_retrofit2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, NetWorkHttpClientActivity::class.java)
        when (v?.id) {
            R.id.btn_network_httpClient -> {
                intent.putExtra("Method", "HttpClient")
                startActivity(intent)
            }
            R.id.btn_network_http_url_connection -> {
                intent.putExtra("Method", "HttpURLConnection")
                startActivity(intent)
            }
            R.id.btn_network_okhttp -> {
                intent.putExtra("Method", "OkHttp")
                startActivity(intent)
            }
            R.id.btn_network_async_okhttp -> {
                startActivity(Intent(this, NetWorkAsyncOKHttpActivity::class.java))
            }
            R.id.btn_network_retrofit2 -> {
                startActivity(Intent(this, RetrofitDemoActivity::class.java))
            }
        }

    }
}
