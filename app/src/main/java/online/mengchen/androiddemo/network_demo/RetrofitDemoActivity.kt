package online.mengchen.androiddemo.network_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.network_demo.service.BookService
import online.mengchen.androiddemo.network_demo.bean.Book
import online.mengchen.androiddemo.network_demo.bean.BookPage
import kotlinx.android.synthetic.main.activity_retrofit_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDemoActivity : AppCompatActivity() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.93.235.44:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_demo)
        val log = network_retrofit2_log
        val bookService: BookService = retrofit.create(BookService::class.java)
        btn_network_retrofit2_get_all.setOnClickListener {
            bookService.getAllBook().enqueue(object: Callback<BookPage> {
                override fun onResponse(call: Call<BookPage>, response: Response<BookPage>) {
                    log.append("\n${response.body()?.bookData?.books}")
                }

                override fun onFailure(call: Call<BookPage>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}
