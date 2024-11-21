package com.example.data.dto.response.concept

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Content (
    val id: Int,
    val name: String,
    val profilePrice: Int,
    val rating: Int,
    val portfolioUrls: List<String>,
    val profileURL: String
)