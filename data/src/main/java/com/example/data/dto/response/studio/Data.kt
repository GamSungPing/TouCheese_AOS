package com.example.data.dto.response.studio

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Data(
    val id: Int,
    val name: String,
    val profilePrice: Int,
    val rating: Int,
    val region: Region
)