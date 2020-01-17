package com.example.androiddemo.storage_demo.entity

data class Book(var id: Int? = null,
                var name: String = "",
                var author: String = "")


object BookNote {
    const val TABLE_NAME = "book"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_AUTHOR = "author"
}