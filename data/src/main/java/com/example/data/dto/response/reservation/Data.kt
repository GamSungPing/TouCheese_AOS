package com.example.data.dto

data class Data(
    val id: Int,
    val reservationDate: String,
    val reservationStatus: String,
    val reservationTime: ReservationTime,
    val studioImage: String,
    val studioName: String
)