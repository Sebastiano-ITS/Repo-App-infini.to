package com.example.infinito.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R

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
            Toast.makeText(holder.itemView.context, "Hai cliccato su: ${currentItem.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = cardList.size
}