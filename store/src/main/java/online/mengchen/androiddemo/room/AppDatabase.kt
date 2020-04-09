package online.mengchen.androiddemo.storage_demo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import online.mengchen.androiddemo.storage_demo.room.entity.Book
import online.mengchen.androiddemo.storage_demo.room.dao.BookDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    private class BookDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var bookDao = database.bookDao()

                    // Delete All
                    bookDao.deleteAll()

                    // Add some book
                    var book = Book(1, "三国演义", "罗贯中", 111.11)
                    bookDao.insert(book)
                    book = Book(2, "西游记", "吴承恩", 222.22)
                    bookDao.insert(book)
                }
            }
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): AppDatabase {
            val tempDatabase = INSTANCE
            if (tempDatabase != null) {
                return tempDatabase
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book"
                ).addCallback(BookDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}