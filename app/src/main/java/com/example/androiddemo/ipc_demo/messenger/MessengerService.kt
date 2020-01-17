package com.example.androiddemo.ipc_demo.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.androiddemo.ipc_demo.utils.MyConstants

class MessengerService: Service() {

    companion object {
        const val TAG = "MessageService"
    }

    private val mMessenger = Messenger(MessageHandler())


    private class MessageHandler: Handler() {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MyConstants.MSG_FROM_CLIENT -> {
                    Log.d(TAG, "receive msg from Client: ${msg.data.getString("msg")}")
                    val client = msg.replyTo
                    val replyMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVER)
                    replyMessage.data = Bundle().apply {
                        putString("reply", "嗯，你的消息我已经收到了，稍后回复你。")
                    }
                    try {
                        client.send(replyMessage)
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mMessenger.binder
    }




}