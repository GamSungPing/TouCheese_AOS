package com.example.data.dto.response.reservation

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudioData(
    val studioName: String,
    val availableSlots: List<String>,
    val openingTime: String,
    val lastReservationTime: String
)