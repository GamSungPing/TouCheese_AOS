package com.example.domain.model

data class ProductDetail (
    val productName: String,
    val productPrice: Int,
    val isGroup: Boolean,
    val basePeopleCnt: Int?,
    val addPeoplePrice: Int?,
    val productOptions: List<String>?
){
    companion object{
        fun create(): ProductDetail{
            return ProductDetail(
                productName = "",
                productPrice = 0,
                isGroup = false,
                basePeopleCnt = null,
                addPeoplePrice = null,
                productOptions = null
            )
        }
    }
}