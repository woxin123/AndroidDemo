package online.mengchen.androiddemo.storage_demo.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import online.mengchen.androiddemo.storage_demo.room.entity.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE bid = :bid")
    fun findById(bid: Long): Book

    @Query("SELECT * FROM book WHERE book_name = :bookName")
    fun findByName(bookName: String): Book

    @Insert
    suspend fun insert(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("DELETE FROM book")
    suspend fun deleteAll()
}