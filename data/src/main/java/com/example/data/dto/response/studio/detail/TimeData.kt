package com.example.data.dto.response.studio.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeData(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val nano: Int
)