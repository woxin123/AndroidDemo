package online.mengchen.androiddemo.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room_book.*
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.room.adapter.BookListAdapter
import online.mengchen.androiddemo.storage_demo.room.BookViewModel
import online.mengchen.androiddemo.storage_demo.room.entity.Book

class RoomBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private val newBookRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_book)

        val adapter = BookListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        bookViewModel.allBooks.observe(this, Observer {books ->
            books?.let { adapter.setBooks(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(this@RoomBookActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newBookRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newBookRequestCode) {
            data?.let { data ->
                val bookName = data.getStringExtra("bookName")!!
                val author = data.getStringExtra("author")!!
                val price = data.getDoubleExtra("price", 0.0)
                val book = Book(null, bookName, author, price)
                bookViewModel.insert(book)
                Unit
            }
        } else {
            Toast.makeText(this@RoomBookActivity, "内容为空", Toast.LENGTH_SHORT).show()
        }
    }
}
