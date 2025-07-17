package com.example.infinito.ui.ticket

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.data.model.ticket.TicketModel
import com.example.infinito.data.model.ticket.TicketUtils
import com.example.infinito.ui.adapter.TicketAdapter
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.ContactFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme

class TicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        setFixedTheme(this, window)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(ActivityNames.TICKET))
            .add(R.id.contactMenu, ContactFragment())
            .commit()

        supportFragmentManager.executePendingTransactions()

        supportFragmentManager
            .beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.contactMenu)!!)
            .commit()

        val tickets = TicketUtils.getTickets(this)

        val ticketList = findViewById<RecyclerView>(R.id.ticketRecyclerView)
        ticketList.adapter = TicketAdapter(tickets)
        ticketList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}