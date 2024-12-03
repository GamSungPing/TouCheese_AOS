package com.example.data.dto.response.concept

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data (
    val totalPagesCount: Int,
    val pageNumber: Int,
    val content: List<Content>
)