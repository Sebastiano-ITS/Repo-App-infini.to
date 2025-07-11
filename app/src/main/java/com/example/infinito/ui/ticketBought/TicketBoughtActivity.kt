package com.example.infinito.ui.ticketBought

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.data.model.TicketDetailModel

class TicketBoughtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket_bought)

        var numberOfTickets = 1
        val ticketBoughtArray = ArrayList<TicketDetailModel>()
        val ticketBought = TicketDetailModel("Serata Osservativa","25/07/2025", "14:30","Intera", 14.00)

        for (i in 1..numberOfTickets) {
            ticketBoughtArray.add(ticketBought)
        }

        val ticketList = findViewById<RecyclerView>(R.id.ticketRecyclerView)
        ticketList.adapter = TicketBoughtAdapter(ticketBoughtArray)
        ticketList.layoutManager = LinearLayoutManager(this)

    }


}