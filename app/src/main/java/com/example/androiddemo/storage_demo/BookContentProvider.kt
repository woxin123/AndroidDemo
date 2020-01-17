package com.example.androiddemo.storage_demo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.androiddemo.storage_demo.entity.BookNote

class BookContentProvider : ContentProvider() {

    private lateinit var dbHelper: BookDataBaseHelper

    companion object {
        const val AUTHORITY = "top.mcwebsite.storage.provider"
        const val BOOK_DIR = 0
        const val BOOK_ITEM = 1
        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, BookNote.TABLE_NAME, BOOK_DIR)
            uriMatcher.addURI(AUTHORITY, BookNote.TABLE_NAME + "/#", BOOK_ITEM)
        }
    }

    override fun onCreate(): Boolean {
        dbHelper = BookDataBaseHelper(context!!)
        return true
    }

    /**
     * 查询数据
     */
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                cursor = db.query(
                    BookNote.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                cursor = db.query(
                    BookNote.TABLE_NAME,
                    projection,
                    "id = ?",
                    arrayOf(bookId),
                    null,
                    null,
                    sortOrder
                )
            }
        }
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        var uriReturn: Uri? = null
        when (uriMatcher.match(uri)) {
            BOOK_DIR, BOOK_ITEM -> {
                val newBookId = db.insert(BookNote.TABLE_NAME, null, values)
                uriReturn = Uri.parse("content://$AUTHORITY/${BookNote.TABLE_NAME}/$newBookId")
            }
        }
        return uriReturn
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val db = dbHelper.writableDatabase
        var updateRows: Int = 0
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                updateRows = db.update(BookNote.TABLE_NAME, values, selection, selectionArgs)
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                updateRows = db.update(BookNote.TABLE_NAME, values, "id = ?", arrayOf(bookId))
            }
        }
        return updateRows
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        var deleteRows: Int = 0
        when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                deleteRows = db.delete(BookNote.TABLE_NAME, selection, selectionArgs)
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                deleteRows = db.delete(BookNote.TABLE_NAME, "id = ?", arrayOf(bookId))
            }
        }
        return deleteRows
    }

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        BOOK_DIR -> "vnd.android.cursor.dir/vnd.$AUTHORITY.${BookNote.TABLE_NAME}"
        BOOK_ITEM -> "vnd.android.cursor.item/vnd.$AUTHORITY.${BookNote.TABLE_NAME}"
        else -> null
    }


}
