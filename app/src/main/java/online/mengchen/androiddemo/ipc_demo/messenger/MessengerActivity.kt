package online.mengchen.androiddemo.ipc_demo.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.ipc_demo.utils.MyConstants

class MessengerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MessengerActivity"
    }

    private lateinit var mService: Messenger

    private val mGetReplyMessenger = Messenger(MessengerHandler())

    private class MessengerHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MyConstants.MSG_FROM_SERVER ->
                    Log.d(MessengerService.TAG, "receive msg from Service: ${msg.data.getString("reply")}")
                else -> super.handleMessage(msg)
            }
        }
    }

    private val mConnection = object: ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            val msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT)
            val data = Bundle()
            data.putString("msg", "hello, this is client.")
            msg.data = data
            msg.replyTo = mGetReplyMessenger
            try {
                mService.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        Log.d(TAG, "onCreate")
        bindService(Intent().apply {
            setClass(this@MessengerActivity, MessengerService::class.java)
        }, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }
}
