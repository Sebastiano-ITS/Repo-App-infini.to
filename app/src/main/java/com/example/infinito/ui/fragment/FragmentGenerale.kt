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
import com.example.infinito.ui.event.EventDetailActivity
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
                imageResId = R.drawable.planetario_telescopio, // Assicurati di avere questa immagine
                title = "PLANETARIO + TELESCOPIO (nuova serata osservativa)",
                calendarInfo = "Tutti i weekend",
                timeInfo = "18:00 - 20:00",
                priceInfo = "Adulti: 12€, Bambini: 8€",
                description = "- Visita libera al museo (30 minuti), con audioguida fruibile da smartphone (portare auricolari).\n\n- Spettacolo sul cielo della stagione nel planetario digitale (30 minuti)",
                longDescription = "- Visita libera al museo (30 minuti), con audioguida fruibile da smartphone (portare auricolari).\n\n- Spettacolo sul cielo della stagione nel planetario digitale (30 minuti)\n\n- L'osservazione del cielo a occhio nudo e al telescopio dalla terrazza (45 minuti)"
            ),
            EventItem(
                id = "museo_astrotalk",
                imageResId = R.drawable.museo_astrotalk, // Assicurati di avere questa immagine
                title = "MUSEO + ASTROTALK",
                calendarInfo = "Ogni Martedì e Giovedì",
                timeInfo = "10:00 - 12:00",
                priceInfo = "Ingresso: 10€",
                description = "Esplora il museo e partecipa a un dibattito interattivo con un astronomo.",
                longDescription = "Esplora le affascinanti esposizioni del museo e partecipa a un dibattito interattivo condotto da un astronomo esperto. Un'occasione unica per approfondire le tue conoscenze sul cosmo e porre domande dirette agli esperti del settore."
            ),
            EventItem(
                id = "spazio_bambini",
                imageResId = R.drawable.spazio_bambini, // Assicurati di avere questa immagine
                title = "LO SPAZIO AI BAMBINI (solo il primo week end del mese)",
                calendarInfo = "Primo weekend del mese",
                timeInfo = "15:00 - 17:00",
                priceInfo = "Bambini: 5€, Adulti: gratis",
                description = "Attività ludiche ed educative per i più piccoli sul tema dello spazio.",
                longDescription = "Un'esperienza dedicata ai nostri giovani esploratori! Attività ludiche ed educative progettate per far scoprire ai bambini i segreti dello spazio in modo divertente e interattivo. Laboratori creativi, storie stellari e giochi a tema per stimolare la curiosità scientifica."
            ),
            EventItem(
                id = "cinema_sotto_stelle",
                imageResId = R.drawable.cinema_sotto_stelle, // Assicurati di avere questa immagine
                title = "CINEMA SOTTO LE STELLE apertura serale",
                calendarInfo = "Venerdì e Sabato sera",
                timeInfo = "21:00",
                priceInfo = "Biglietto: 7€",
                description = "Proiezioni di film a tema spaziale all'aperto.",
                longDescription = "Goditi una serata magica con proiezioni di film a tema spaziale sotto il cielo stellato. Un'esperienza cinematografica unica che unisce il fascino del grande schermo alla bellezza dell'universo. Porta la tua coperta e preparati a viaggiare tra le stelle!"
            )
        )
    }
}