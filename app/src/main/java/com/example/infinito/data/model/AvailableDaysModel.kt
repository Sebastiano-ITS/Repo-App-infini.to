package com.example.infinito.data.model

data class InfinitoItem(
    val day: Int,
    val available: Boolean,
    val availabilityNotDetermined: Boolean
)

data class InfinitoApiResponse(
    val dayList: List<InfinitoItem>
)