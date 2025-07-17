package com.example.infinito.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.data.model.ticket.TicketModel

class TicketAdapter(private val ticketsBought: List<TicketModel>) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ticket_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = ticketsBought[position]
        val eventTitle = holder.itemView.findViewById<TextView>(R.id.titleEventText)
        eventTitle.text = ticket.eventTitle
        val ticketDate = holder.itemView.findViewById<TextView>(R.id.dateTicketText)
        ticketDate.text = ticket.ticketDate
        val ticketTime = holder.itemView.findViewById<TextView>(R.id.timeTicketText)
        ticketTime.text = ticket.ticketTime
        val ticketLocation = holder.itemView.findViewById<TextView>(R.id.typeTicketText)
        ticketLocation.text = ticket.ticketType
        val ticketPrice = holder.itemView.findViewById<TextView>(R.id.priceTicketText)
        ticketPrice.text = "%.2f".format(ticket.ticketPrice)
    }

    override fun getItemCount(): Int {
        return ticketsBought.size
    }
}