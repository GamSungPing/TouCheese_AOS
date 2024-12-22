package com.example.domain.model

data class Like(
    val id: String,
    val name: String,
    val profilePrice: String,
    val rating: String,
    val portfolioUrls: List<String>,
    val profileURL: String,
    val reviewCount: Int
)