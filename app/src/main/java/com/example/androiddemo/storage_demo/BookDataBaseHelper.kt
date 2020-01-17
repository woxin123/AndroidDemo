package com.example.androiddemo.storage_demo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * context
 * database name
 * cursor factory
 * version
 */
class BookDataBaseHelper(ctx: Context): SQLiteOpenHelper(ctx, "book.db", null, 1) {

    companion object {
        const val CREATE_BOOK = """
            CREATE TABLE book(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name VARCHAR(255),
                author VARCHAR(255)
            )"""
        const val TAG = "BookDataBaseHelper"
    }

    /**
     * 创建数据库的时候调用
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOK)
        Log.d(TAG, "数据库创建成功")
    }

    /**
     * 数据库升级的时候调用
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}