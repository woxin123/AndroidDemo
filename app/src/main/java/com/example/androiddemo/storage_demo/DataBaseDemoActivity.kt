package com.example.androiddemo.storage_demo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.CursorWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.androiddemo.R
import com.example.androiddemo.storage_demo.entity.Book
import com.example.androiddemo.storage_demo.entity.BookNote

class DataBaseDemoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var createDataBase: Button
    private lateinit var addData: Button
    private lateinit var updateData: Button
    private lateinit var deleteData: Button
    private lateinit var queryData: Button
    private lateinit var textView: TextView
    private lateinit var dbHelper: BookDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_demo)
        createDataBase = findViewById(R.id.btn_storage_data_base_create)
        addData = findViewById(R.id.btn_storage_data_base_add)
        updateData = findViewById(R.id.btn_storage_data_base_update)
        deleteData = findViewById(R.id.btn_storage_data_base_delete)
        queryData = findViewById(R.id.btn_storage_data_base_query)
        createDataBase.setOnClickListener(this)
        addData.setOnClickListener(this)
        updateData.setOnClickListener(this)
        deleteData.setOnClickListener(this)
        queryData.setOnClickListener(this)
        textView = findViewById(R.id.storage_data_base_text_view)
        dbHelper = BookDataBaseHelper(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_storage_data_base_create -> {
                dbHelper.writableDatabase
            }
            R.id.btn_storage_data_base_add -> {
                val write = dbHelper.writableDatabase
                val value = ContentValues()
                val book = Book(name = "三国演义", author = "罗贯中")
                value.put(BookNote.COLUMN_NAME, book.name)
                value.put(BookNote.COLUMN_AUTHOR, book.author)
                write.insert(BookNote.TABLE_NAME, null, value)
                textView.append("\n插入 $book 成功")
                write.close()
            }
            R.id.btn_storage_data_base_query -> {
                val read = dbHelper.readableDatabase
                val cursor = read.query(BookNote.TABLE_NAME, null, null, null, null, null, null)
                val books = mutableListOf<Book>()
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val book = Book()
                    book.id = cursor.getInt(cursor.getColumnIndex(BookNote.COLUMN_ID))
                    book.name = cursor.getString(cursor.getColumnIndex(BookNote.COLUMN_NAME))
                    book.author = cursor.getString(cursor.getColumnIndex(BookNote.COLUMN_AUTHOR))
                    books.add(book)
                    cursor.moveToNext()
                }
                val result = "查询结果如下: $books"
                textView.append("\n $result")
            }
            R.id.btn_storage_data_base_update -> {
                val write = dbHelper.writableDatabase
                val value = ContentValues()
                value.put(BookNote.COLUMN_NAME, "西游记")
                value.put(BookNote.COLUMN_AUTHOR, "罗贯中")
                val update = write.update(BookNote.TABLE_NAME, value, "id = ?", arrayOf("1"))
                if (update != 0)
                    textView.append("\n修改成功")
                else
                    textView.append("\n修改失败")
                write.close()
            }
            R.id.btn_storage_data_base_delete -> {
                val write = dbHelper.writableDatabase
                write.delete(BookNote.TABLE_NAME, "id = ?", arrayOf("1"))
                write.close()
            }
        }
    }

    override fun finish() {
        super.finish()
        dbHelper.close()
    }
}
