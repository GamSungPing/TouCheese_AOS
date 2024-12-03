package com.example.domain.model

data class Product (
    val id: String,
    val name: String,
    val description: String,
    val imageString: String,
    val price: Int,
    val reviewCnt: Int,
    val isGroup: Boolean
)