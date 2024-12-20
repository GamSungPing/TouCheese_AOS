package com.example.data.dto.response.productdetail

internal data class Data(
    val name: String,
    val price: Int,
    val isGroup: Boolean,
    val basePeopleCnt: Int,
    val addPeoplePrice: Int,
    val productOptions: List<String>
)