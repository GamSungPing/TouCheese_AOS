package com.example.data.dto.response.conceptlist

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data(
    val id: Int,
    val name: String,
    val mainUrl: String
)
