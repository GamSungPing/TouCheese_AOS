package com.example.data.dto.response.product

internal data class Data (
    val id: String,
    val name: String,
    val description: String,
    val imageString: String,
    val price: Int,
    val reviewCount: Int
)