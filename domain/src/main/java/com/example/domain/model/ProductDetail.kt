package com.example.domain.model

data class ProductDetail (
    val isGroup: Boolean,
    val baseGuestCount: Int?,
    val addPeoplePrice: Int?,
    val productOptions: List<String>
)