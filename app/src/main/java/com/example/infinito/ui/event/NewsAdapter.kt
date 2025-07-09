package com.example.infinito.ui.event

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R

class NewsAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.newsCardImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.newsCardTitleTextView)
        // Non abbiamo bisogno di un riferimento all'icona del link esterno perché il click è sulla card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.titleTextView.text = currentItem.title

        holder.itemView.setOnClickListener {
            // Quando la card viene cliccata, apri l'URL esterno
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.url))
            holder.itemView.context.startActivity(browserIntent)
        }
    }

    override fun getItemCount() = newsList.size
}