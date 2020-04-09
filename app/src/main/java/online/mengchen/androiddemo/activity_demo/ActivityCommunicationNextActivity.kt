package online.mengchen.androiddemo.activity_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.activity_demo.bean.Person
import kotlinx.android.synthetic.main.activity_communication_next.*

class ActivityCommunicationNextActivity : AppCompatActivity() {

    private var person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication_next)
        person.username = "李四"
        person.nickname = "小四"
        intent.getParcelableExtra<Person>("person").let {
            activity_communication_next_text_view.append("${it}\n")
        }
        btn_activity_communication_back.setOnClickListener {
            setResult()
            finish()
        }
    }

    private fun setResult() {
        setResult(RESULT_OK, Intent().apply {
            putExtras(Bundle().apply {
                putParcelable("person", person)
            })
        })
    }

    override fun onBackPressed() {
        setResult()
        finish()
    }
}
