package online.mengchen.androiddemo.storage_demo.room.repository

import androidx.lifecycle.LiveData
import online.mengchen.androiddemo.storage_demo.room.dao.BookDao
import online.mengchen.androiddemo.storage_demo.room.entity.Book

class BookRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<Book>> = bookDao.getAll()

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
}