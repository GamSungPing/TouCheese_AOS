package com.example.data.dto.response.studio.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudioData(
    val studioId: Int,
    val name: String,
    val detailImageStrings: List<String>,
    val rating: Float,
    val reviewCount: Int,
    val openTime: String,
    val closeTime: String,
    val holidays: List<Int>,
    val address: String,
    val notice: String,
    val products: List<Product>,
    val reviews: Reviews
)