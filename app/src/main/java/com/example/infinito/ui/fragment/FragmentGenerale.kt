package com.example.infinito.ui.fragment

import android.content.Intent // Importa Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infinito.R
import com.example.infinito.ui.event.EventItem
import com.example.infinito.ui.event.EventAdapter
import com.example.infinito.ui.event_detail.EventDetailActivity
import com.example.infinito.ui.event.OnEventClickListener


class GeneralFragment : Fragment(), OnEventClickListener { // Implementa l'interfaccia OnEventClickListener

    private lateinit var generalRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        generalRecyclerView = view.findViewById(R.id.recyclerView)
        generalRecyclerView.layoutManager = LinearLayoutManager(context)

        val eventItems = generateDummyEventData() // Genera i dati degli eventi
        val adapter = EventAdapter(eventItems, this) // Passa 'this' come listener
        generalRecyclerView.adapter = adapter

        return view
    }

    // Implementa i metodi dell'interfaccia OnEventClickListener
    override fun onEventCardClick(event: EventItem) {
        openEventDetail(event)
    }

    override fun onEventButtonClick(event: EventItem) {
        // Se il bottone "Scopri di più" fa la stessa cosa del clic sulla card
        openEventDetail(event)
        // Oppure se fa qualcosa di diverso, implementalo qui
    }

    private fun openEventDetail(event: EventItem) {
        val intent = Intent(activity, EventDetailActivity::class.java)
        intent.putExtra("event_item", event) // Passa l'oggetto EventItem
        startActivity(intent)
    }

    private fun generateDummyEventData(): List<EventItem> {
        return listOf(
            EventItem(
                id = "planetario_telescopio",
                imageResId = R.drawable.planetario_telescopio,
                title = R.string.title_planetario,
                calendarInfo = "Tutti i weekend",
                availableTimes = listOf("14:30", "16:00", "17:30", "18:45"),
                priceInfo = "Adulti: 12€, Bambini: 8€",
                longDescription = R.string.planetario_telescopio_long_description,
                ),
            EventItem(
                id = "museo_astrotalk",
                imageResId = R.drawable.museo_astrotalk,
                title = R.string.title_astrotalk,
                calendarInfo = "Ogni Martedì e Giovedì",
                availableTimes = listOf("10:30", "11:30", "15:00", "17:00"),
                priceInfo = "Ingresso: 10€",
                longDescription = R.string.museo_astrotalk_long_description,
            ),
            EventItem(
                id = "spazio_bambini",
                imageResId = R.drawable.spazio_bambini,
                title = R.string.title_bambini,
                calendarInfo = "Primo weekend del mese",
                availableTimes = listOf("14:30", "16:00", "17:30"),
                priceInfo = "Bambini: 5€, Adulti: gratis",
                longDescription = R.string.spazio_bambini_long_description
            ),
            EventItem(
                id = "cinema_sotto_stelle",
                imageResId = R.drawable.cinema_sotto_stelle,
                title = R.string.title_cinema,
                calendarInfo = "Venerdì e Sabato sera",
                availableTimes = listOf("19:15", "19:30"),
                priceInfo = "Biglietto: 7€",
                longDescription = R.string.cinema_sotto_stelle_long_description
            )
        )
    }
}