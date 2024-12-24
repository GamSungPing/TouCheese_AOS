package com.example.data.dto.request

internal data class RefreshRequest(
    val accessToken: String,
    val refreshToken: String,
)