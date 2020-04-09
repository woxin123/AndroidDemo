package online.mengchen.androiddemo.ipc_demo.aidl;

import online.mengchen.androiddemo.ipc_demo.aidl.Book;
import online.mengchen.androiddemo.ipc_demo.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}