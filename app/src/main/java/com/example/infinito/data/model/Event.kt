package com.example.infinito.data.model

import java.time.LocalDateTime

data class Event(
    val title: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val availableSeats: Int
)
