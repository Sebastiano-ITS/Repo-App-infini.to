package com.example.infinito.ui.event

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.HeaderFragment

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val activeTab = intent.getStringExtra("active_tab") ?: "home"

        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .add(R.id.bottomBar, BottomBarFragment.newInstance(activeTab))
            .commit()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cardItems = generateDummyData()
        val adapter = CardAdapter(cardItems)
        recyclerView.adapter = adapter

        val cardGenerale: CardView = findViewById(R.id.cardGenerale)
        val cardNews: CardView = findViewById(R.id.cardNews)

        cardGenerale.setOnClickListener {
            Toast.makeText(this, "Hai cliccato su Generale", Toast.LENGTH_SHORT).show()
        }

        cardNews.setOnClickListener {
            Toast.makeText(this, "Hai cliccato su News", Toast.LENGTH_SHORT).show()
        }


    }

    private fun generateDummyData(): List<CardItem> {
        return listOf(
            CardItem(R.drawable.planetario_telescopio, "PLANETARIO + TELESCOPIO\n(nuova serata osservativa)"),
            CardItem(R.drawable.museo_astrotalk, "MUSEO + ASTROTALK"),
            CardItem(R.drawable.spazio_bambini, "LO SPAZIO AI BAMBINI\n(solo il primo week end del mese)"),
            CardItem(R.drawable.cinema_sotto_stelle, "CINEMA SOTTO LE STELLE\napertura serate")
        )
    }
}