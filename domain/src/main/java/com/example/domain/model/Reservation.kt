package com.example.domain.model

data class Reservation(
    val id: Int,
    val reservationDate: String,
    val reservationStatus: String,
    val reservationTime: String,
    val studioImage: String,
    val studioName: String
)