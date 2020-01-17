package com.example.androiddemo.ipc_demo.manualbinder

import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import com.example.androiddemo.ipc_demo.manualbinder.IBookManager.Companion.DESCRIPTOR
import com.example.androiddemo.ipc_demo.manualbinder.IBookManager.Companion.TRANSACTION_addBook
import com.example.androiddemo.ipc_demo.manualbinder.IBookManager.Companion.TRANSACTION_getBookList

class BookManagerImpl : Binder(), IBookManager {

    init {
        this.attachInterface(this, DESCRIPTOR)
    }

    companion object {
        fun asInterface(obj: IBinder?): IBookManager? {
            if (obj == null) {
                return null
            }
            val iin = obj.queryLocalInterface(DESCRIPTOR)
            if ((iin != null) && iin is IBookManager) {
                return iin as IBookManager
            }
            return BookManagerImpl.Proxy(obj)
        }
    }

    override fun getBookList(): List<Book>? {
        // TODO
        return null
    }

    override fun addBook(book: Book?) {
        // TODO
    }

    override fun asBinder(): IBinder {
        return this
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        when (code) {
            IBinder.INTERFACE_TRANSACTION -> {
                reply?.writeString(DESCRIPTOR)
                return true
            }
            TRANSACTION_getBookList -> {
                data.enforceInterface(DESCRIPTOR)
                this.getBookList().let {
                    reply?.writeNoException()
                    reply?.writeTypedList(it)
                }
                return true
            }
            TRANSACTION_addBook -> {
                data.enforceInterface(DESCRIPTOR)
                val arg0: Book? =
                    if (data.readInt() != 0) Book.CREATOR.createFromParcel(data) else null
                this.addBook(arg0)
                reply?.writeNoException()
                return true
            }
        }
        return super.onTransact(code, data, reply, flags)
    }

    class Proxy(val mRemote: IBinder) : IBookManager {

        fun getInterfaceDescription(): String {
            return DESCRIPTOR
        }

        override fun getBookList(): List<Book> {
            val data = Parcel.obtain()
            val reply = Parcel.obtain()
            val result: List<Book>
            try {
                data.writeInterfaceToken(DESCRIPTOR)
                mRemote.transact(TRANSACTION_getBookList, data, reply, 0)
                result = reply.createTypedArrayList(Book.CREATOR)!!
            } finally {
                reply.recycle()
                data.recycle()
            }
            return result
        }

        override fun addBook(book: Book?) {
            val data = Parcel.obtain()
            val reply = Parcel.obtain()
            val result: List<Book>
            try {
                data.writeInterfaceToken(DESCRIPTOR)
                if (book != null) {
                    data.writeInt(1)
                    book.writeToParcel(data, 0)
                } else {
                    data.writeInt(0)
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0)
                reply.readException()
            } finally {
                reply.recycle()
                data.recycle()
            }
        }

        override fun asBinder(): IBinder {
            return mRemote
        }

    }

}