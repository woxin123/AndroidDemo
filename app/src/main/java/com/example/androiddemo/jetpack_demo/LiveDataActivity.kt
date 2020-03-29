package com.example.androiddemo.jetpack_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_live_data.*

class  LiveDataActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "LiveDataActivity"
    }

    private lateinit var mNumberLiveData: MutableLiveData<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        btnStart.setOnClickListener(this)
        mNumberLiveData = MutableLiveData()
        Transformations.map(mNumberLiveData) {
            it.toString()
        }.observe(this, Observer {
            tvNumber.text = it
            Log.d(TAG, "onChange: $it")
        })
    }

    override fun onClick(v: View?) {
        Thread {
            var number = 0
            while (number < 5) {
                Thread.sleep(5000)
                number++
                mNumberLiveData.postValue(number)
            }
        }.start()
    }
}
