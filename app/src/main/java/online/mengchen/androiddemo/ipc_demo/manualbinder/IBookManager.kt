package online.mengchen.androiddemo.ipc_demo.manualbinder

import android.os.IBinder
import android.os.IInterface

interface IBookManager: IInterface {


    companion object {
        const val DESCRIPTOR = "online.mengchen.androiddemo.ipc_demo.manualbinder.IBookManager"
        const val TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 0)
        const val TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1)

    }

    fun getBookList(): List<Book>?
    fun addBook(book: Book?)
}