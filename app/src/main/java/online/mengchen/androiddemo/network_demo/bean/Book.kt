package online.mengchen.androiddemo.network_demo.bean

import com.google.gson.annotations.SerializedName

data class Book(var bookId: Int? = null,
                @SerializedName("bookName")
                var bookName: String? = null,
                @SerializedName("bookAuthor")
                var bookAuthor: String? = null,
                var price: Double? = null)
