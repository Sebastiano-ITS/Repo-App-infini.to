package com.example.infinito.ui.event

import java.io.Serializable // Per passare l'oggetto via Intent

data class EventItem(
    val id: String, // Un ID univoco per l'evento
    val imageResId: Int, // ID risorsa immagine
    val title: String,   // Titolo dell'evento
    val calendarInfo: String,
    val availableTimes: List<String>,
    val priceInfo: String,    // Info tariffa (es. "Adulti: 12€, Bambini: 8€")
    val longDescription: String
) : Serializable