package com.example.infinito.data.model

data class User(
    val name: String,
    val email: String,
    val phone: String? = null,
    val savedTicketIds: List<Int> = emptyList()
)

