package online.mengchen.androiddemo.storage_demo.content_provider_demo

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import online.mengchen.androiddemo.R
import java.lang.Exception

class ReadContactDemoActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var contacts: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_contact_demo)
        val listView: ListView = findViewById(R.id.storage_content_provider_contact_view)
        contacts = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts)
        listView.adapter = adapter
        // 检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        } else {
            readContacts()
        }
    }

    fun readContacts() {
        var cursor: Cursor? = null
        try {
            // 查询联系人
            cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null, null
            )
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //  获取联系人姓名
                    val displayName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    // 获取联系人手机号
                    val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contacts.add("$displayName\n$number")
                    adapter.notifyDataSetChanged()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
