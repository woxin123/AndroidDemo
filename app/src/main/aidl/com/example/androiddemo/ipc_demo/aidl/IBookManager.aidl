// IBookManager.aidl
package com.example.androiddemo.ipc_demo.aidl;

import com.example.androiddemo.ipc_demo.aidl.Book;
import com.example.androiddemo.ipc_demo.aidl.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
