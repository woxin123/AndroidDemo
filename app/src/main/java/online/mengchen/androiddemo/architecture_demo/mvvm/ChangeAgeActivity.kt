package online.mengchen.androiddemo.architecture_demo.mvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.databinding.ActivityChangeAgeBinding


class ChangeAgeActivity : AppCompatActivity() {

    lateinit var viewModel: ChangeAgeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChangeAgeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_change_age)
        viewModel = ViewModelProviders.of(this).get(ChangeAgeViewModel::class.java)
        binding.viewModel = viewModel
        binding.buttonname = "年龄+2"
        viewModel.desc.observe(this,
            Observer<String> { t -> Log.d("desc", t) })

        binding.btnArchitectureMvvmAge.setOnClickListener {
            viewModel.change()
        }
    }
}
