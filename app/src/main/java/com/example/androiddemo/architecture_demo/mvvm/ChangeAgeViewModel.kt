package com.example.androiddemo.architecture_demo.mvvm

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.properties.ObservableProperty

class ChangeAgeViewModel(application: Application): AndroidViewModel(application) {
    val desc = MutableLiveData<String>()
    val age  = ObservableField<String>()

    init {
        age.set(23.toString())
    }

    fun change() {
        val value = age.get()!!
        val ageInt = value.toInt()
        age.set((ageInt + 2).toString())
        desc.value = "年龄改变: ${age.get()}"
    }
}