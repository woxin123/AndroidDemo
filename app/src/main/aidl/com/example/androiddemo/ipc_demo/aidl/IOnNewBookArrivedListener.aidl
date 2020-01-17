// IOnNewBookArrivedListener.aidl
package com.example.androiddemo.ipc_demo.aidl;

import com.example.androiddemo.ipc_demo.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
