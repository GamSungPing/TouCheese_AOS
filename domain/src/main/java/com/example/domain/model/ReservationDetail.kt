package com.example.domain.model

data class ReservationDetail(
    val id: Int,
    val studioImg: String,
    val studioId: Int,
    val studioName: String,
    val phoneNumber: String,
    val memberName: String,
    val memberEmail: String,
    val reservationDate: String,
    val reservationTime: String,
    val productName: String,
    val productOption: String,
    val totalPrice: Int,
    val studioAddress: String,
    val reservationStatus: String
)