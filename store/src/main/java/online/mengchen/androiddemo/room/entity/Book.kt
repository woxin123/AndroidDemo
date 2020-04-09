package online.mengchen.androiddemo.storage_demo.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Entity 表示将一个 class 声明为实体类
 */

@Entity(tableName = "book")
data class Book (
    @PrimaryKey(autoGenerate = true) val bid: Long? = null,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "price") val price: Double
)