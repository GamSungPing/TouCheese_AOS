package com.example.domain.model

data class ReservationDetail(
    val id: Int,
    val memberEmail: String,
    val memberName: String,
    val phoneNumber: String,
    val productOption: String,
    val reservationDate: String,
    val reservationTime: String,
    val studioAddress: String,
    val studioName: String,
    val totalPrice: Int
)