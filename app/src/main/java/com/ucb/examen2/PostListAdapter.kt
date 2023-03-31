package com.ucb.examen2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostListAdapter(val items: ArrayList<Post>, val context: Context): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        //establecer que layout va a usar layout.book_row
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostListViewHolder(v)
    }
    override fun getItemCount(): Int {
        return items.count()
    }
    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val item = items.get(position)
        holder.itemView.findViewById<TextView>(R.id.titleTextView).text = item.title
        holder.itemView.findViewById<TextView>(R.id.bodyTextView).text = item.body

    }
    class PostListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}