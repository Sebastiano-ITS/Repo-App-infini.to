package com.example.infinito.ui.event_detail

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.R
import com.example.infinito.data.model.TariffDetail
import com.example.infinito.ui.event.CalendarBottomSheetFragment
import com.example.infinito.ui.event.EventItem
import com.example.infinito.ui.event.OnDateSelectedListener
import com.example.infinito.ui.event.OnTariffSelectedListener
import com.example.infinito.ui.event.OnTimeSelectedListener
import com.example.infinito.ui.event.TariffBottomSheetFragment
import com.example.infinito.ui.event.TimeBottomSheetFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventDetailActivity : AppCompatActivity(), OnDateSelectedListener, OnTimeSelectedListener,
    OnTariffSelectedListener { // Implementa l'interfaccia

    // Tutte le dichiarazioni lateinit var
    private lateinit var detailImageView: ImageView
    private lateinit var detailTitleTextView: TextView
    private lateinit var detailDescriptionTextView: TextView
    private lateinit var buyButton: MaterialButton

    private lateinit var calendarCardButton: MaterialCardView
    private lateinit var timeCardButton: MaterialCardView
    private lateinit var priceCardButton: MaterialCardView

    private lateinit var selectedDateTextView: TextView
    private lateinit var selectedTimeTextView: TextView

    private lateinit var selectedTariffTextView: TextView


    private var currentEvent: EventItem? = null // per tenere traccia dell'evento corrente

    private var selectedEventDate: LocalDate? = null
    private var selectedEventTime: String? = null

    private var selectedEventTariff: TariffDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        setFixedTheme(this, window)

        detailImageView = findViewById(R.id.detailImageView)
        detailTitleTextView = findViewById(R.id.detailTitleTextView)
        detailDescriptionTextView = findViewById(R.id.detailDescriptionTextView)
        buyButton = findViewById(R.id.buyButton)

        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        selectedTimeTextView = findViewById(R.id.selectedTimeTextView)
        selectedTariffTextView = findViewById(R.id.selectedTariffTextView)


        supportFragmentManager
            .beginTransaction()
            .add(R.id.header, HeaderFragment())
            .commit()

        calendarCardButton = findViewById(R.id.calendarCardButton)
        timeCardButton = findViewById(R.id.timeCardButton)
        priceCardButton = findViewById(R.id.priceCardButton)

        val event: EventItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("event_item", EventItem::class.java)
        } else {
            intent.getSerializableExtra("event_item") as? EventItem
        }

        event?.let {
            currentEvent = it // Salva l'evento corrente
            detailImageView.setImageResource(it.imageResId)
            detailTitleTextView.text = getString(it.title)
            detailDescriptionTextView.text = getString(it.longDescription)
        }

        // Settaggio dei listeners
        calendarCardButton.setOnClickListener {
            // Crea e mostra il Bottom Sheet del calendario
            val calendarBottomSheet = CalendarBottomSheetFragment()
            calendarBottomSheet.onDateSelectedListener = this
            calendarBottomSheet.show(supportFragmentManager, calendarBottomSheet.tag)
        }

        timeCardButton.setOnClickListener {
            val timeBottomSheet = TimeBottomSheetFragment()
            timeBottomSheet.onTimeSelectedListener = this
            timeBottomSheet.show(supportFragmentManager, timeBottomSheet.tag)
        }

        timeCardButton.setOnClickListener {
            currentEvent?.let { eventItem ->
                // Passa gli orari specifici dell'evento al TimeBottomSheetFragment
                val timeBottomSheet = TimeBottomSheetFragment.newInstance(eventItem.availableTimes)
                timeBottomSheet.onTimeSelectedListener = this
                timeBottomSheet.show(supportFragmentManager, timeBottomSheet.tag)
            } ?: run {
                Toast.makeText(this, "Errore: Dati evento non disponibili per gli orari.", Toast.LENGTH_SHORT).show()
            }
        }

        priceCardButton.setOnClickListener {
            currentEvent?.let { eventItem ->
                val tariffBottomSheet =
                    TariffBottomSheetFragment.newInstance(eventItem.availableTariffs) // Passa le tariffe specifiche
                tariffBottomSheet.onTariffSelectedListener = this
                tariffBottomSheet.show(supportFragmentManager, tariffBottomSheet.tag)
            } ?: run {
                Toast.makeText(this, "Errore: Dati evento non disponibili per le tariffe.", Toast.LENGTH_SHORT).show()
            }
        }


        buyButton.setOnClickListener {
            if (selectedEventDate != null && selectedEventTime != null && selectedEventTariff != null) {
                val formattedDate = selectedEventDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                val tariffDisplayName = selectedEventTariff?.getDisplayName(this)
                val tariffTotal = selectedEventTariff?.totale

                Toast.makeText(this, "Data: $formattedDate, Ora: $selectedEventTime, Tariffa: $tariffDisplayName  (Prezzo: €${String.format("%.2f", tariffTotal)})", Toast.LENGTH_LONG).show()
                // QUI puoi passare 'selectedEventDate', 'selectedEventTime' e 'selectedEventTariff' ad altre schermate
                // Esempio:
                // val intent = Intent(this, NextActivity::class.java)
                // intent.putExtra("selectedDate", selectedEventDate.toString()) // Converti a stringa per Intent
                // intent.putExtra("selectedTime", selectedEventTime)
                // intent.putExtra("selectedTariff", selectedEventTariff) // Passa l'oggetto Tariff completo
                // startActivity(intent)
            } else {
                Toast.makeText(this, "Seleziona una data, un orario e una tariffa!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDateSelected(selectedDate: LocalDate) {
        this.selectedEventDate = selectedDate
        // Formatta la data per visualizzarla
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALIAN)
        selectedDateTextView.text = selectedDate.format(formatter) // Aggiorna il testo del TextView
        // Toast.makeText(this, "Data selezionata : ${selectedDate.format(formatter)}", Toast.LENGTH_LONG).show()
    }

    override fun onTimeSelected(selectedTime: String) {
        this.selectedEventTime = selectedTime
        selectedTimeTextView.text = selectedTime
        // Toast.makeText(this, "Orario selezionato : $selectedTime", Toast.LENGTH_SHORT).show()
    }

    override fun onTariffSelected(selectedTariff: TariffDetail) {
        this.selectedEventTariff = selectedTariff
        selectedTariffTextView.text = "${selectedTariff.getDisplayName(this)} (€${String.format("%.2f", selectedTariff.totale)})"
        // Toast.makeText(this, "Tariffa selezionata: ${selectedTariff.tipo} (Prezzo: €${String.format("%.2f", selectedTariff.totale)})", Toast.LENGTH_SHORT).show()
    }
}