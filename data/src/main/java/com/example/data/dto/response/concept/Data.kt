package com.example.data.dto.response.concept

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data (
    val totalElementsCount: Int,
    val pageElementsCount: Int,
    val totalPagesCount: Int,
    val pageNumber: Int,
    val content: List<Content>
)