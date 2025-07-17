package com.example.infinito.data.model

import java.io.Serializable // Per passare l'oggetto via Intent

data class EventModel(
    val id: String, // Un ID univoco per l'evento
    val imageResId: Int, // ID risorsa immagine
    val title: Int,   // Titolo dell'evento
    val calendarInfo: String,
    val availableTimes: List<String>,
    val availableTariffs: List<TariffModel>,
    val longDescription: Int,
    val url: Int
) : Serializable