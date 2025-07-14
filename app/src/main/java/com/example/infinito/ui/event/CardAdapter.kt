package com.example.infinito.ui.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.ui.event_detail.EventDetailActivity

class CardAdapter(private val cardList: List<CardItem>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val learnMoreButton: Button = itemView.findViewById(R.id.learnMoreButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = cardList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.titleTextView.text = currentItem.title

        holder.learnMoreButton.setOnClickListener {
            val eventDetailIntent = Intent(holder.itemView.context, EventDetailActivity::class.java)
            holder.itemView.context.startActivity(eventDetailIntent)
        }
    }

    override fun getItemCount() = cardList.size
}