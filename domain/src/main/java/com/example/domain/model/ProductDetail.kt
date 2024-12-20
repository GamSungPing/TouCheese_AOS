package com.example.domain.model

import com.example.domain.rule.Grouping

data class ProductDetail(
    val productName: String,
    val productPrice: Int,
    val isGroup: Grouping,
    val basePeopleCnt: Int,
    val addPeoplePrice: Int,
    val productOptions: List<ProductOption>?
) {
    companion object {
        fun create(): ProductDetail {
            return ProductDetail(
                productName = "",
                productPrice = 0,
                isGroup = Grouping.NOT_GROUP,
                basePeopleCnt = 0,
                addPeoplePrice = 0,
                productOptions = null
            )
        }
    }
}
