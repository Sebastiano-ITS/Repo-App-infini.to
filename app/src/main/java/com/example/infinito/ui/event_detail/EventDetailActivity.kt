package com.example.infinito.ui.event_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.edit
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.infinito.R
import com.example.infinito.data.model.TariffModel
import com.example.infinito.ui.fragment.CalendarBottomSheetFragment
import com.example.infinito.data.model.EventModel
import com.example.infinito.data.model.InfinitoItem
import com.example.infinito.data.model.ticket.TicketModel
import com.example.infinito.data.model.ticket.TicketUtils
import com.example.infinito.data.model.user.UserModel
import com.example.infinito.data.model.user.UserUtils
import com.example.infinito.ui.fragment.OnDateSelectedListener
import com.example.infinito.ui.fragment.OnTariffSelectedListener
import com.example.infinito.ui.fragment.OnTimeSelectedListener
import com.example.infinito.ui.fragment.TariffBottomSheetFragment
import com.example.infinito.ui.fragment.TimeBottomSheetFragment
import com.example.infinito.ui.fragment.ContactFragment
import com.example.infinito.ui.fragment.HeaderFragment
import com.example.infinito.utils.md5.toMD5
import com.example.infinito.utils.theme.setFixedTheme
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventDetailActivity : AppCompatActivity(), OnDateSelectedListener, OnTimeSelectedListener,
    OnTariffSelectedListener { // Implementa l'interfaccia

    // Tutte le dichiarazioni lateinit var
    private lateinit var viewModel: EventDetailViewModel

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


    private var currentEvent: EventModel? = null // per tenere traccia dell'evento corrente

    private var selectedEventDate: LocalDate? = null
    private var selectedEventTime: String? = null

    private var selectedEventTariff: TariffModel? = null

    private var selectedUrl: Uri? = null

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
            .add(R.id.contactMenu, ContactFragment())
            .commit()

        supportFragmentManager.executePendingTransactions()

        supportFragmentManager
            .beginTransaction()
            .hide(supportFragmentManager.findFragmentById(R.id.contactMenu)!!)
            .commit()

        calendarCardButton = findViewById(R.id.calendarCardButton)
        timeCardButton = findViewById(R.id.timeCardButton)
        priceCardButton = findViewById(R.id.priceCardButton)

        val event: EventModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("event_item", EventModel::class.java)
        } else {
            intent.getSerializableExtra("event_item") as? EventModel
        }

        event?.let {
            currentEvent = it // Salva l'evento corrente
            detailImageView.setImageResource(it.imageResId)
            detailTitleTextView.text = getString(it.title)
            detailDescriptionTextView.text = getString(it.longDescription)
            selectedUrl = getString(it.url).toUri()
        }

        viewModel = ViewModelProvider(this)[EventDetailViewModel::class.java]

        viewModel.fetchAvailableDaysData()

        viewModel.dayList.observe(this) { dayList ->
            if (dayList.isNotEmpty()) {
                val availabilityList = BooleanArray(dayList.size) { index ->
                    dayList[index].available
                }
                Log.d("available list", "Size: ${availabilityList.size}")
            } else {
                Log.d("EventDetailActivity", "dayList è vuoto")
            }
        }

        calendarCardButton.setOnClickListener {
            viewModel.dayList.value?.let { dayList ->
                if (dayList.isNotEmpty()) {
                    val availabilityList = BooleanArray(dayList.size) { index ->
                        dayList[index].available
                    }

                    val calendarBottomSheet = CalendarBottomSheetFragment()
                    calendarBottomSheet.onDateSelectedListener = this
                    calendarBottomSheet.availabilityList = availabilityList
                    calendarBottomSheet.show(supportFragmentManager, calendarBottomSheet.tag)
                } else {
                    Toast.makeText(this, "Dati calendario non disponibili", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "Caricamento dati in corso...", Toast.LENGTH_SHORT).show()
            }
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

                val ticket = TicketModel(detailTitleTextView.text.toString(), formattedDate!!, selectedEventTime!!, tariffDisplayName!!, tariffTotal!!)
                val tickets = TicketUtils.getTickets(this)
                TicketUtils.saveTickets(this, tickets + ticket)

                val browserIntent = Intent(Intent.ACTION_VIEW, selectedUrl)
                startActivity(browserIntent)

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

    override fun onTariffSelected(selectedTariff: TariffModel) {
        this.selectedEventTariff = selectedTariff
        selectedTariffTextView.text = "${selectedTariff.getDisplayName(this)} (€${String.format("%.2f", selectedTariff.totale)})"
        // Toast.makeText(this, "Tariffa selezionata: ${selectedTariff.tipo} (Prezzo: €${String.format("%.2f", selectedTariff.totale)})", Toast.LENGTH_SHORT).show()
    }
}