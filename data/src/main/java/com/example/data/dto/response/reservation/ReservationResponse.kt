package com.example.data.dto.response.reservation

data class Reservation(
    val `data`: List<Data>,
    val msg: String,
    val statusCode: Int
)