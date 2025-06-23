package com.example.infinito.data.model

import java.time.LocalDateTime

data class Ticket(
    val purchaseDate: LocalDateTime,
    val ticketType: TicketType,
    val price: Double
)

enum class TicketType {
    FULL,
    REDUCED,
    FREE
}
