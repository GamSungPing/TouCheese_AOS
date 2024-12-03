package com.example.data.dto.response.studio.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewContent(
    val reviewId: Int,
    val imageUrl: String
)