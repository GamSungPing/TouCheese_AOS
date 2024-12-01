package com.example.data.dto.response.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
)