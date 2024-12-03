package com.example.data.dto.response.studio.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val imageString: String,
    val reviewCnt: Int,
    val price: Int,
    val isGroup: Boolean
)