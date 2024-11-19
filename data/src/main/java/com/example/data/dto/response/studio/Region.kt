package com.example.data.dto.response.studio

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Region(
    val name: String
)