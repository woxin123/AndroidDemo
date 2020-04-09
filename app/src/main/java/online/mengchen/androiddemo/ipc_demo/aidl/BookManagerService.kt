package online.mengchen.androiddemo.ipc_demo.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.os.RemoteException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

class BookManagerService: Service() {

    companion object {
        const val TAG = "BMS"
    }

    private val mIsServiceDestroy = AtomicBoolean(false)

    private val mBookList = CopyOnWriteArrayList<Book>()

//    private val mListenerList = CopyOnWriteArrayList<IOnNewBookArrivedListener>()

    private val mListenerList = RemoteCallbackList<IOnNewBookArrivedListener>()

    private val mBinder = object: IBookManager.Stub() {

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

        override fun registerListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.register(listener)
        }

        override fun unregisterListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.unregister(listener)
        }
    }

    override fun onCreate() {
        super.onCreate()
        init()
        Thread(ServiceWorker()).start()
    }

    private fun init() {
        mBookList.add(Book(1, "三国演义"))
        mBookList.add(Book(2, "西游记"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    private fun onNewBookArrived(book: Book) {
        mBookList.add(book)
        val n = mListenerList.beginBroadcast()
        for (i in 0 until n) {
            val l = mListenerList.getBroadcastItem(i)
            if (l != null) {
                try {
                    l.onNewBookArrived(book)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
        mListenerList.finishBroadcast()
    }

    private inner class ServiceWorker: Runnable {

        override fun run() {
            while (!mIsServiceDestroy.get()) {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                val bookId = mBookList.size + 1
                val book = Book(bookId, "newBook#$bookId")
                try {
                    onNewBookArrived(book)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }


}