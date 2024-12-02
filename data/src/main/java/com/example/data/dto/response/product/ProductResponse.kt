package com.example.data.dto.response.product

import com.example.domain.model.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductResponse(
    val data: List<Data>,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): List<Product> {
        return data.map {
            Product(
                id = it.id.toString(),
                name = it.name,
                description = it.description,
                imageString = it.imageString,
                price = it.price,
                reviewCnt = it.reviewCnt,
                isGroup = it.isGroup
            )
        }
    }
}