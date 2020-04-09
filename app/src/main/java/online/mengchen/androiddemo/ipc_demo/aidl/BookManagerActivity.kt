package online.mengchen.androiddemo.ipc_demo.aidl

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import online.mengchen.androiddemo.R

class BookManagerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "BookManagerActivity"
        const val MESSAGE_NEW_BOOK_ARRIVED = 1
    }

    private var mRemoteBookManager: IBookManager? = null

    private val mHandler = @SuppressLint("HandlerLeak")
    object: Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_NEW_BOOK_ARRIVED -> {
                    Log.d(TAG, "received new book: ${msg.obj}")
                }
            }
        }
    }

    private val mConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            mRemoteBookManager = null
            Log.e(TAG, "binder died.")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bookManager = IBookManager.Stub.asInterface(service)
            mRemoteBookManager = bookManager
            try {
                val list = bookManager.bookList
                Log.d(TAG, "query book list: $list")
                val newBook = Book(3, "红楼梦")
                bookManager.addBook(newBook)
                Log.d(TAG, "add book: $newBook")
                val newList = bookManager.bookList
                Log.d(TAG, "query book list: $newList")
                bookManager.registerListener(onNewBookArrivedListener)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

    }

    private val onNewBookArrivedListener = object: IOnNewBookArrivedListener.Stub() {

        override fun onNewBookArrived(newBook: Book?) {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook)
                .sendToTarget()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_manager)
        bindService(
            Intent(this, BookManagerService::class.java), mConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mRemoteBookManager != null && mRemoteBookManager?.asBinder()?.isBinderAlive!!) {
            try {
                Log.d(TAG, "unregister listener: $onNewBookArrivedListener")
                mRemoteBookManager?.unregisterListener(onNewBookArrivedListener)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        unbindService(mConnection)
    }
}
