package com.example.domain.model

data class LikeRequest(
    val createdAt: String,
    val memberId: Int,
    val studioId: Int
)