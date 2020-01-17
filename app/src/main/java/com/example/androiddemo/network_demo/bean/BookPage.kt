package com.example.androiddemo.network_demo.bean

import com.google.gson.annotations.SerializedName

class BookData {
    @SerializedName("books")
    var books: List<Book>? = null
}

class BookPage {

    @SerializedName("_embedded")
    var bookData: BookData? = null


}