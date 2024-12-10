package com.example.data.dto.response.reservation

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data(
    val id: Int,
    val reservationDate: String,
    val reservationStatus: String,
    val reservationTime: String,
    val studioImage: String,
    val studioName: String
)