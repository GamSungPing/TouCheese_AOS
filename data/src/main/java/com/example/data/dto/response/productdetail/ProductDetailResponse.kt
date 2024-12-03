package com.example.data.dto.response.productdetail

import android.util.Log
import com.example.domain.model.ProductDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductDetailResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): ProductDetail {
        return ProductDetail(
            isGroup = data.isGroup,
            baseGuestCount = data.baseGuestCount,
            addPeoplePrice = data.addPeoplePrice,
            productOptions = data.productOptions
        )
    }
}