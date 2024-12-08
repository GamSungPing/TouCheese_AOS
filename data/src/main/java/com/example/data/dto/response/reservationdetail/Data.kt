package com.example.data.dto.response.reservationdetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data(
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