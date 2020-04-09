package online.mengchen.androiddemo.ipc_demo.aidl;

import online.mengchen.androiddemo.ipc_demo.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}