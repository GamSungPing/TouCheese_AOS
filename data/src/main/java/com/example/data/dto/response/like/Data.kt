package com.example.data.dto.response.like

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data(
    val id: String,
    val name: String,
    val profilePrice: String,
    val rating: String,
    val portfolioUrls: List<String>,
    val profileURL: String,
    val reviewCount: Int
)