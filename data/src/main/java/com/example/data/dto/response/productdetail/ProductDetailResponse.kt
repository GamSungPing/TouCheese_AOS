package com.example.data.dto.response.productdetail

import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductOption
import com.example.domain.rule.Grouping
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductDetailResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): ProductDetail {
        val option = if (data.productOptions.isNotEmpty()) {
            data.productOptions.map {
                val spliterator = it.split(":", limit = 2)
                ProductOption(
                    name = spliterator[0],
                    price = spliterator[1].toInt()
                )
            }
        } else {
            null
        }
        val group = Grouping.from(data.isGroup)
        return ProductDetail(
            productName = data.name,
            productPrice = data.price,
            basePeopleCnt = data.basePeopleCnt,
            addPeoplePrice = data.addPeoplePrice,
            productOptions = option,
            isGroup = group
        )
    }
}