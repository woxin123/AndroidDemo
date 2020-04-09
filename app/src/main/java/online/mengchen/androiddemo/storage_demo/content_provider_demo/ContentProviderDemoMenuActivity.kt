package online.mengchen.androiddemo.storage_demo.content_provider_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.mengchen.androiddemo.R

class ContentProviderDemoMenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var readContacts: Button
    private lateinit var contentProviderDemo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_demo_menu)
        readContacts = findViewById(R.id.btn_storage_content_provider_contact)
        readContacts.setOnClickListener(this)
        contentProviderDemo = findViewById(R.id.btn_storage_content_provider_demo)
        contentProviderDemo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_storage_content_provider_contact -> Intent(this, ReadContactDemoActivity::class.java)
            R.id.btn_storage_content_provider_demo -> Intent(this, ContentProviderDemoActivity::class.java)
            else -> null
        }?.let { startActivity(it) }
    }
}
