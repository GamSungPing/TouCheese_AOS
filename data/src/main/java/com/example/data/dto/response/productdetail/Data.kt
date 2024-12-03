package com.example.data.dto.response.productdetail

internal data class Data(
    val isGroup: Boolean,
    val baseGuestCount: Int?,
    val addPeoplePrice: Int?,
    val productOptions: List<String>
)