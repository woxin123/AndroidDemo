package online.mengchen.androiddemo.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_book.*
import online.mengchen.androiddemo.R

class NewBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        save.setOnClickListener {
            val bookName = bookName.text.toString()
            val author = author.text.toString()
            val price = price.text.toString().toDouble()
            if (bookName.isEmpty() || author.isEmpty()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val replayIntent = Intent()
                replayIntent.putExtra("bookName", bookName)
                replayIntent.putExtra("author", author)
                replayIntent.putExtra("price", price)
            }
            finish()
        }
    }
}
