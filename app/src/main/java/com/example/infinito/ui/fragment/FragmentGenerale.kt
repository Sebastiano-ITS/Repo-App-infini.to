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
import com.example.infinito.data.model.TariffDetail
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
                imageResId = R.drawable.planetario_telescopio,
                title = "PLANETARIO + TELESCOPIO (nuova serata osservativa)",
                calendarInfo = "Tutti i weekend",
                availableTimes = listOf("14:30", "16:00", "17:30", "18:45"),
                availableTariffs = listOf(
                    TariffDetail("Intera", "19-30 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Intera", "31-60 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Ridotto", "Ragazzi 6-11 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Ragazzi 12-18 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Over 60", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "tessere/enti convenzionati", null, 11.00, 1.00, 12.00),
                    TariffDetail("Gratuito", null, "Disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Accompagnatore disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", "Bambini 3-5 anni", null, 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Passaporto culturale \"Nati con la Cultura\"", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Residenti Pino Torinese", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "dipendenti Osservatorio Astrofisico di Torino/ex IFSI", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Club Docenti di Infiniti.to", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuità", null, "Abbonamento Musei", 0.00, 1.00, 1.00),
                    TariffDetail("Gratuità", null, "Torino+Piemonte Card", 0.00, 1.00, 1.00),
                    TariffDetail("Mondo Parchi", null, null, 13.00, 1.00, 14.00),
                    TariffDetail("Vivi Parchi", null, null, 13.00, 1.00, 14.00)
                ),
                longDescription = "- Visita libera al museo (30 minuti), con audioguida fruibile da smartphone (portare auricolari).\n\n- Spettacolo sul cielo della stagione nel planetario digitale (30 minuti)\n\n- L'osservazione del cielo a occhio nudo e al telescopio dalla terrazza (45 minuti)"
            ),
            EventItem(
                id = "museo_astrotalk",
                imageResId = R.drawable.museo_astrotalk,
                title = "MUSEO + ASTROTALK",
                calendarInfo = "Ogni Martedì e Giovedì",
                availableTimes = listOf("10:30", "11:30", "15:00", "17:00"),
                availableTariffs = listOf(
                    TariffDetail("Intera", "19-30 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Intera", "31-60 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Ridotto", "Ragazzi 6-11 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Ragazzi 12-18 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Over 60", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "tessere/enti convenzionati", null, 11.00, 1.00, 12.00),
                    TariffDetail("Gratuito", null, "Disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Accompagnatore disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", "Bambini 3-5 anni", null, 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Passaporto culturale \"Nati con la Cultura\"", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Residenti Pino Torinese", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "dipendenti Osservatorio Astrofisico di Torino/ex IFSI", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Club Docenti di Infiniti.to", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuità", null, "Abbonamento Musei", 0.00, 1.00, 1.00),
                    TariffDetail("Gratuità", null, "Torino+Piemonte Card", 0.00, 1.00, 1.00),
                    TariffDetail("Mondo Parchi", null, null, 13.00, 1.00, 14.00),
                    TariffDetail("Vivi Parchi", null, null, 13.00, 1.00, 14.00)
                ),
                longDescription = "Un ciclo di incontri con scienziati e ricercatori che esplorano l’Universo. Dalle meteoriti che cadono sulla Terra alle galassie più lontane, attraverso il Sistema Solare, i buchi neri e i raggi cosmici.\n" +
                        "Gli Astrotalk sono conferenze-spettacolo che uniscono le ultime ricerche in campo astronomico e scientifico alle spettacolari immagini del Planetario digitale. \n \n \n " +
                        "Cosa comprende il biglietto \n \n" +
                        "- L'Astrotalk in planetario (45 minuti)\n" +
                        "- Uno spettacolo del planetario (30 minuti)\n" +
                        "- La visita libera del museo interattivo (1h)\n" +
                        "- L’audioguida fruibile da smartphone (portare auricolari)" +
                        "\n \n Cosa non comprende il biglietto \n" +
                        "- La visita guidata del museo interattivo"
            ),
            EventItem(
                id = "spazio_bambini",
                imageResId = R.drawable.spazio_bambini,
                title = "LO SPAZIO AI BAMBINI (solo il primo week end del mese)",
                calendarInfo = "Primo weekend del mese",
                availableTimes = listOf("14:30", "16:00", "17:30"),
                availableTariffs = listOf(
                    TariffDetail("Intera", "19-30 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Intera", "31-60 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Ridotto", "Ragazzi 6-11 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Ragazzi 12-18 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Over 60", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "tessere/enti convenzionati", null, 11.00, 1.00, 12.00),
                    TariffDetail("Gratuito", null, "Disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Accompagnatore disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", "Bambini 3-5 anni", null, 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Passaporto culturale \"Nati con la Cultura\"", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Residenti Pino Torinese", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "dipendenti Osservatorio Astrofisico di Torino/ex IFSI", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Club Docenti di Infiniti.to", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuità", null, "Abbonamento Musei", 0.00, 1.00, 1.00),
                    TariffDetail("Gratuità", null, "Torino+Piemonte Card", 0.00, 1.00, 1.00),
                    TariffDetail("Mondo Parchi", null, null, 13.00, 1.00, 14.00),
                    TariffDetail("Vivi Parchi", null, null, 13.00, 1.00, 14.00)
                ),
                longDescription = "Il primo weekend di ogni mese, con tre turni di visita: arrivo alle ore 14.30*, 16.00* o 17.30\n" +
                        "\n" +
                        "*Al biglietto di ingresso delle 14.30 o delle 16.00 puoi aggiungere il funlab 6-11 anni (attività a pagamento per il bambino 6-11 anni, un adulto accompagna gratis)" +
                        "\n \n Cosa comprende il biglietto\n" +
                        "- La visita libera del museo interattivo (1h);\n" +
                        "- L’audioguida fruibile da smartphone (portare auricolari);\n" +
                        "- Uno spettacolo del planetario dedicato ai bambini (30 minuti)." +
                        "\n \n Cosa non comprende il biglietto\n" +
                        "- Il laboratorio funlab 6-11 anni (1h). Va aggiunto al biglietto di ingresso (bambino paga + adulto accompagna gratis);\n" +
                        "- La visita guidata del museo interattivo.\n"
            ),
            EventItem(
                id = "cinema_sotto_stelle",
                imageResId = R.drawable.cinema_sotto_stelle,
                title = "CINEMA SOTTO LE STELLE apertura serale",
                calendarInfo = "Venerdì e Sabato sera",
                availableTimes = listOf("19:15", "19:30"),
                availableTariffs = listOf(
                    TariffDetail("Intera", "19-30 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Intera", "31-60 anni", null, 13.00, 1.00, 14.00),
                    TariffDetail("Ridotto", "Ragazzi 6-11 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Ragazzi 12-18 anni", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "Over 60", null, 11.00, 1.00, 12.00),
                    TariffDetail("Ridotto", "tessere/enti convenzionati", null, 11.00, 1.00, 12.00),
                    TariffDetail("Gratuito", null, "Disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Accompagnatore disabile", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", "Bambini 3-5 anni", null, 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Passaporto culturale \"Nati con la Cultura\"", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Residenti Pino Torinese", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "dipendenti Osservatorio Astrofisico di Torino/ex IFSI", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuito", null, "Club Docenti di Infiniti.to", 0.00, 0.00, 0.00),
                    TariffDetail("Gratuità", null, "Abbonamento Musei", 0.00, 1.00, 1.00),
                    TariffDetail("Gratuità", null, "Torino+Piemonte Card", 0.00, 1.00, 1.00),
                    TariffDetail("Mondo Parchi", null, null, 13.00, 1.00, 14.00),
                    TariffDetail("Vivi Parchi", null, null, 13.00, 1.00, 14.00)
                ),
                longDescription = "Goditi una serata magica con proiezioni di film a tema spaziale sotto il cielo stellato. Un'esperienza cinematografica unica che unisce il fascino del grande schermo alla bellezza dell'universo. Porta la tua coperta e preparati a viaggiare tra le stelle!"
            )
        )
    }
}