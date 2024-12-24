package com.example.data.dto.response.login

data class Data (
    val accessToken: String,
    val refreshToken: String,
    val memberId: Long,
    val memberName: String,
)