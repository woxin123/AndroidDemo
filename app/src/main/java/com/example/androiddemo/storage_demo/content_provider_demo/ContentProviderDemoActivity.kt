package com.example.androiddemo.storage_demo.content_provider_demo

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.androiddemo.R
import com.example.androiddemo.storage_demo.BookContentProvider
import com.example.androiddemo.storage_demo.entity.Book
import com.example.androiddemo.storage_demo.entity.BookNote

class ContentProviderDemoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var add: Button
    private lateinit var update: Button
    private lateinit var query: Button
    private lateinit var delete: Button
    private lateinit var textView: TextView
    private var newId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_demo)
        add = findViewById(R.id.btn_storage_content_provider_demo_add)
        update = findViewById(R.id.btn_storage_content_provider_demo_update)
        query = findViewById(R.id.btn_storage_content_provider_demo_query)
        delete = findViewById(R.id.btn_storage_content_provider_demo_delete)
        textView = findViewById(R.id.storage_content_provider_demo_text_view)
        add.setOnClickListener(this)
        update.setOnClickListener(this)
        query.setOnClickListener(this)
        delete.setOnClickListener(this)
        textView.append("日志如下:")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_storage_content_provider_demo_add -> {
                // 添加数据
                val uri = Uri.parse("content://${BookContentProvider.AUTHORITY}/${BookNote.TABLE_NAME}")
                val values = ContentValues()
                values.put(BookNote.COLUMN_NAME, "水浒传")
                values.put(BookNote.COLUMN_AUTHOR, "施耐庵")
                val newUri = contentResolver.insert(uri, values)!!
                newId = newUri.pathSegments[1]
                textView.append("\n添加Book:[$newId, 水浒传, 施耐庵]")
            }
            R.id.btn_storage_content_provider_demo_query -> {
                // 查询数据
                val uri = Uri.parse("content://${BookContentProvider.AUTHORITY}/${BookNote.TABLE_NAME}")
                val cursor = contentResolver.query(uri, null, null, null, null)
                if (cursor != null) {
                    textView.append("\n查询结果如下:")
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(cursor.getColumnIndex(BookNote.COLUMN_NAME))
                        val author = cursor.getString(cursor.getColumnIndex(BookNote.COLUMN_AUTHOR))
                        val id = cursor.getInt(cursor.getColumnIndex(BookNote.COLUMN_ID))
                        val book = Book(id, name, author)
                        textView.append("\n$book")
                    }
                }
                cursor?.close()
            }

            R.id.btn_storage_content_provider_demo_update -> {
                // 更新数据
                val uri = Uri.parse("content://${BookContentProvider.AUTHORITY}/${BookNote.TABLE_NAME}/$newId")
                val values = ContentValues()
                values.put(BookNote.COLUMN_NAME, "红楼梦")
                values.put(BookNote.COLUMN_AUTHOR, "曹雪芹")
                val newUri = contentResolver.insert(uri, values)!!
                newId = newUri.pathSegments[1]
                textView.append("\n更新Book:[$newId, 红楼梦, 曹雪芹]")
            }
            R.id.btn_storage_content_provider_demo_delete -> {
                // 删除数据
                val uri = Uri.parse("content://${BookContentProvider.AUTHORITY}/${BookNote.TABLE_NAME}/$newId")
                contentResolver.delete(uri, null, null)
            }
        }
    }

}
