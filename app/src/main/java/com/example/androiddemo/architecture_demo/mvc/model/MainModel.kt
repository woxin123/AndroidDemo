package com.example.androiddemo.architecture_demo.mvc.model

class MainModel {
    interface MainImpl {
        fun success(text: String)
    }

    private var mMain: MainImpl? = null

    fun load(main: MainImpl) {
        this.mMain = main
        val text = "MVC 模式在 Android 中的应用，获取到 Model 中处理数据"
        main.success(text)
    }
}