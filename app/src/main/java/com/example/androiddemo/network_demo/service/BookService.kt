package com.example.androiddemo.network_demo.service

import com.example.androiddemo.network_demo.bean.Book
import com.example.androiddemo.network_demo.bean.BookPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("book/{bookId}")
    fun getBookById(@Path("bookId") bookId: Int): Call<Book>

    @GET("book")
    fun getAllBook(): Call<BookPage>
}