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
                longDescription = R.string.planetario_telescopio_long_description,
                availableTariffs = listOf(
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_19_30_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_31_60_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_6_11_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_12_18_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_over_60, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_tessere_enti_convenzionati, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_accompagnatore_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, R.string.tariff_fascia_bambini_3_5_anni, null, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_passaporto_culturale, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_residenti_pino_torinese, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_dipendenti_osservatorio, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_club_docenti, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_abbonamento_musei, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_torino_piemonte_card, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_mondo_parchi, null, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_vivi_parchi, null, null, 13.00, 1.00, 14.00)
                )
            ),
            EventItem(
                id = "museo_astrotalk",
                imageResId = R.drawable.museo_astrotalk,
                title = R.string.title_astrotalk,
                calendarInfo = "Ogni Martedì e Giovedì",
                longDescription = R.string.museo_astrotalk_long_description,
                availableTimes = listOf("10:30", "11:30", "15:00", "17:00"),
                availableTariffs = listOf(
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_19_30_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_31_60_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_6_11_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_12_18_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_over_60, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_tessere_enti_convenzionati, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_accompagnatore_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, R.string.tariff_fascia_bambini_3_5_anni, null, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_passaporto_culturale, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_residenti_pino_torinese, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_dipendenti_osservatorio, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_club_docenti, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_abbonamento_musei, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_torino_piemonte_card, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_mondo_parchi, null, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_vivi_parchi, null, null, 13.00, 1.00, 14.00)
                )

            ),
            EventItem(
                id = "spazio_bambini",
                imageResId = R.drawable.spazio_bambini,
                title = R.string.title_bambini,
                calendarInfo = "Primo weekend del mese",
                availableTimes = listOf("14:30", "16:00", "17:30"),
                longDescription = R.string.spazio_bambini_long_description,
                availableTariffs = listOf(
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_19_30_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_31_60_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_6_11_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_12_18_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_over_60, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_tessere_enti_convenzionati, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_accompagnatore_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, R.string.tariff_fascia_bambini_3_5_anni, null, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_passaporto_culturale, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_residenti_pino_torinese, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_dipendenti_osservatorio, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_club_docenti, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_abbonamento_musei, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_torino_piemonte_card, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_mondo_parchi, null, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_vivi_parchi, null, null, 13.00, 1.00, 14.00)
                )
            ),
            EventItem(
                id = "cinema_sotto_stelle",
                imageResId = R.drawable.cinema_sotto_stelle,
                title = R.string.title_cinema,
                calendarInfo = "Venerdì e Sabato sera",
                availableTimes = listOf("19:15", "19:30"),
                longDescription = R.string.cinema_sotto_stelle_long_description,
                availableTariffs = listOf(
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_19_30_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_intera, R.string.tariff_fascia_31_60_anni, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_6_11_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_ragazzi_12_18_anni, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_over_60, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_ridotto, R.string.tariff_fascia_tessere_enti_convenzionati, null, 11.00, 1.00, 12.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_accompagnatore_disabile, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, R.string.tariff_fascia_bambini_3_5_anni, null, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_passaporto_culturale, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_residenti_pino_torinese, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_dipendenti_osservatorio, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuito, null, R.string.tariff_categoria_club_docenti, 0.00, 0.00, 0.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_abbonamento_musei, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_gratuita, null, R.string.tariff_categoria_torino_piemonte_card, 0.00, 1.00, 1.00),
                    TariffDetail(R.string.tariff_tipo_mondo_parchi, null, null, 13.00, 1.00, 14.00),
                    TariffDetail(R.string.tariff_tipo_vivi_parchi, null, null, 13.00, 1.00, 14.00)
                )
            ),
        )
    }
}