package com.example.data.dto.response.productdetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductDetailResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
)