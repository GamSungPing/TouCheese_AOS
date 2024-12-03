package com.example.data.dto.response.studio.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reviews(
    val totalPagesCount: Int,
    val pageNumber: Int,
    val content: List<ReviewContent>
)