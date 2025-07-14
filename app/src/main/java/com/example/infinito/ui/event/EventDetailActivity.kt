package com.example.infinito.ui.event

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.infinito.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import android.widget.Toast
import com.example.infinito.data.model.ActivityNames
import com.example.infinito.ui.event.CalendarBottomSheetFragment
import com.example.infinito.ui.event.OnDateSelectedListener
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.event.TimeBottomSheetFragment
import com.example.infinito.ui.event.OnTimeSelectedListener
import com.example.infinito.ui.fragment.HeaderFragment
import java.time.LocalDate // Richiede Android API Level 26+
import java.time.format.DateTimeFormatter
import java.util.Locale



class EventDetailActivity : AppCompatActivity(), OnDateSelectedListener, OnTimeSelectedListener  { // Implementa l'interfaccia

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


    private var currentEvent: EventItem? = null // per tenere traccia dell'evento corrente

    private var selectedEventDate: LocalDate? = null
    private var selectedEventTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        detailImageView = findViewById(R.id.detailImageView)
        detailTitleTextView = findViewById(R.id.detailTitleTextView)
        detailDescriptionTextView = findViewById(R.id.detailDescriptionTextView)
        buyButton = findViewById(R.id.buyButton)

        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        selectedTimeTextView = findViewById(R.id.selectedTimeTextView)


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
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("event_item") as? EventItem
        }

        event?.let {
            currentEvent = it // Salva l'evento corrente
            detailImageView.setImageResource(it.imageResId)
            detailTitleTextView.text = it.title
            detailDescriptionTextView.text = it.longDescription
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
            Toast.makeText(this, "Modale Tariffa: ${event?.priceInfo}", Toast.LENGTH_LONG).show()
        }


        buyButton.setOnClickListener {
            if (selectedEventDate != null && selectedEventTime != null) {
                val formattedDate = selectedEventDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                // Toast.makeText(this, "Data scelta: $formattedDate, Ora: $selectedEventTime", Toast.LENGTH_LONG).show()
                // QUI puoi passare 'selectedEventDate' e 'selectedEventTime' ad altre schermate
                // Esempio:
                // val intent = Intent(this, NextActivity::class.java)
                // intent.putExtra("selectedDate", selectedEventDate.toString()) // Converti a stringa per Intent
                // intent.putExtra("selectedTime", selectedEventTime)
                // startActivity(intent)
            } else {
                Toast.makeText(this, "Seleziona una data e un orario!", Toast.LENGTH_SHORT).show()
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
}