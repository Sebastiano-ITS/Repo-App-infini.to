package com.example.infinito.ui.event

import com.example.infinito.data.model.TariffDetail
import java.io.Serializable // Per passare l'oggetto via Intent

data class EventItem(
    val id: String, // Un ID univoco per l'evento
    val imageResId: Int, // ID risorsa immagine
    val title: Int,   // Titolo dell'evento
    val calendarInfo: String,
    val availableTimes: List<String>,
    val availableTariffs: List<TariffDetail>,
    val longDescription: Int
) : Serializable