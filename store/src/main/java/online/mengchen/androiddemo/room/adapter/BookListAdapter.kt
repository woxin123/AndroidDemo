package online.mengchen.androiddemo.room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import online.mengchen.androiddemo.storage_demo.room.entity.Book
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import online.mengchen.androiddemo.R

class BookListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    private val inflate: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookName: TextView = itemView.findViewById(R.id.bookName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflate.inflate(R.layout.recyclerview_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        holder.bookName.text = current.bookName
    }

    internal fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

}