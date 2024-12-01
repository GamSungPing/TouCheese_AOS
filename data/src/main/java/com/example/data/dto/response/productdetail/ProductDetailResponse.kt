package com.example.data.dto.response.productdetail

import com.example.domain.model.ProductDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductDetailResponse(
    val data: List<Data>,
    val msg: String,
    val statusCode: Int
){
    fun toDomainModel(): List<ProductDetail> {
        return data.map {
            ProductDetail(
                isGroup =  it.isGroup,
                baseGuestCount = it.baseGuestCount,
                addPeoplePrice = it.addPeoplePrice,
                productOptions = it.productOptions
            )
        }
    }
}