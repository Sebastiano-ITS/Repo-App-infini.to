package com.example.infinito.ui.event

import java.io.Serializable // Per passare l'oggetto via Intent

data class EventItem(
    val id: String, // Un ID univoco per l'evento, utile per il recupero dei dettagli
    val imageResId: Int, // ID risorsa immagine (es. R.drawable.planetario_telescopio)
    val title: String,   // Titolo dell'evento (es. "PLANETARIO + TELESCOPIO")
    val calendarInfo: String, // Info calendario (es. "Tutti i weekend")
    val timeInfo: String,     // Info orario (es. "18:00 - 20:00")
    val priceInfo: String,    // Info tariffa (es. "Adulti: 12€, Bambini: 8€")
    val description: String,  // Descrizione dettagliata
    val longDescription: String // Descrizione estesa per la schermata di dettaglio
) : Serializable