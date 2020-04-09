package online.mengchen.androiddemo.architecture_demo.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.architecture_demo.mvc.bean.Eassy
import online.mengchen.androiddemo.architecture_demo.mvc.model.EassyModel
import online.mengchen.androiddemo.architecture_demo.mvc.model.MainModel

class LoadDataActivity : AppCompatActivity(), View.OnClickListener {

    val btnMvcLoad by lazy { findViewById<Button>(R.id.btn_architecture_mvc_load) }
    val btnMvcUpdate by lazy { findViewById<Button>(R.id.btn_architecture_mvc_update) }
    val textView by lazy { findViewById<TextView>(R.id.architecture_mvc_text_view) }
    val textViewUpdate by lazy { findViewById<TextView>(R.id.architecture_mvc_text_view_update) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_data)
        setTitle("Mvc")
        btnMvcLoad.setOnClickListener(this)
        btnMvcUpdate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_architecture_mvc_load ->
                MainModel().load(object: MainModel.MainImpl {
                    override fun success(text: String) {
                        textView.text = text
                    }
                })
            R.id.btn_architecture_mvc_update -> {
                val eassyModel = EassyModel(this)
                eassyModel.getEassy(3, object: EassyModel.OnEassyListener {
                    override fun onSuccess(list: MutableList<Eassy>) {
                        textViewUpdate.text = "MVC 更新数据: ${list[0].title}"
                    }

                    override fun onError() {

                    }
                })
            }
        }
    }
}
