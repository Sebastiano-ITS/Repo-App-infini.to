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
import com.example.infinito.ui.event.OnDateSelectedListener // Importa l'interfaccia
import com.example.infinito.ui.fragment.BottomBarFragment
import com.example.infinito.ui.fragment.HeaderFragment
import java.time.LocalDate // Richiede Android API Level 26+
import java.time.format.DateTimeFormatter
import java.util.Locale

private lateinit var selectedDateTextView: TextView

class EventDetailActivity : AppCompatActivity(), OnDateSelectedListener { // Implementa l'interfaccia

    // Tutte le dichiarazioni lateinit var
    private lateinit var detailImageView: ImageView
    private lateinit var detailTitleTextView: TextView
    private lateinit var detailDescriptionTextView: TextView
    private lateinit var buyButton: MaterialButton
    private lateinit var userIcon: ImageView
    private lateinit var bookmarkIcon: ImageView
    private lateinit var logoText: TextView

    private lateinit var calendarCardButton: MaterialCardView
    private lateinit var timeCardButton: MaterialCardView
    private lateinit var priceCardButton: MaterialCardView



    // Variabile per memorizzare la data scelta
    private var selectedEventDate: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        // Inizializza tutte le viste con findViewById
        detailImageView = findViewById(R.id.detailImageView)
        detailTitleTextView = findViewById(R.id.detailTitleTextView)
        detailDescriptionTextView = findViewById(R.id.detailDescriptionTextView)
        buyButton = findViewById(R.id.buyButton)

        selectedDateTextView = findViewById(R.id.selectedDateTextView)


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
            detailImageView.setImageResource(it.imageResId)
            detailTitleTextView.text = it.title
            detailDescriptionTextView.text = it.longDescription
        }

        // Settaggio dei listeners
        calendarCardButton.setOnClickListener {
            // Crea e mostra il Bottom Sheet del calendario
            val calendarBottomSheet = CalendarBottomSheetFragment()
            calendarBottomSheet.onDateSelectedListener = this // Imposta l'Activity come listener
            calendarBottomSheet.show(supportFragmentManager, calendarBottomSheet.tag)
        }

        timeCardButton.setOnClickListener {
            Toast.makeText(this, "Modale Orario: ${event?.timeInfo}", Toast.LENGTH_LONG).show()
        }

        priceCardButton.setOnClickListener {
            Toast.makeText(this, "Modale Tariffa: ${event?.priceInfo}", Toast.LENGTH_LONG).show()
        }


        buyButton.setOnClickListener {
            selectedEventDate?.let { date ->
                Toast.makeText(this, "Data scelta: ${date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}", Toast.LENGTH_SHORT).show()
                // QUI puoi passare la 'date' ad altre schermate o logiche
                // Esempio: Invia la data a una nuova Activity
                // val intent = Intent(this, NextActivity::class.java)
                // intent.putExtra("selected_date", date.toString()) // LocalDate deve essere convertita in Stringa per Extra
                // startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Seleziona prima una data dal calendario!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Implementazione dell'interfaccia OnDateSelectedListener
    override fun onDateSelected(selectedDate: LocalDate) {
        this.selectedEventDate = selectedDate
        // Formatta la data per visualizzarla
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALIAN) // Esempio: "25 Luglio 2025"
        selectedDateTextView.text = selectedDate.format(formatter) // Aggiorna il testo del TextView
        Toast.makeText(this, "Data selezionata in Activity: ${selectedDate.format(formatter)}", Toast.LENGTH_LONG).show()
    }
}