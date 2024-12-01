package com.example.data.dto.response.product

import com.example.domain.model.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): Product {
        return Product(
            id = data.id,
            name = data.name,
            description = data.description,
            imageString = data.imageString,
            price = data.price,
            reviewCount = data.reviewCount,
            isGroup = data.isGroup
        )
    }
}