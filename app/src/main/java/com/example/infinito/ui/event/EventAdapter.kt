package com.example.infinito.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R

// Definisci un'interfaccia per gestire i clic
interface OnEventClickListener {
    fun onEventCardClick(event: EventItem)
    fun onEventButtonClick(event: EventItem)
}

class EventAdapter(
    private val eventList: List<EventItem>,
    private val listener: OnEventClickListener // Aggiungi il listener al costruttore
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView.findViewById(R.id.eventCard) // Riferimento all'intera card
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val button: MaterialButton = itemView.findViewById(R.id.learnMoreButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = eventList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.titleTextView.text = currentItem.title

        // Imposta il clic sull'intera card
        holder.card.setOnClickListener {
            listener.onEventCardClick(currentItem)
        }

        // Imposta il clic sul bottone "Scopri di più"
        holder.button.setOnClickListener {
            listener.onEventButtonClick(currentItem)
        }
    }

    override fun getItemCount() = eventList.size
}