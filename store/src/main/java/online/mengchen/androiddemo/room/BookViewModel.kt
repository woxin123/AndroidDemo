package online.mengchen.androiddemo.storage_demo.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import online.mengchen.androiddemo.storage_demo.room.entity.Book
import online.mengchen.androiddemo.storage_demo.room.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BookRepository

    val allBooks: LiveData<List<Book>>

    init {
        val bookDao = AppDatabase.getDatabase(application, viewModelScope).bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)
    }
}