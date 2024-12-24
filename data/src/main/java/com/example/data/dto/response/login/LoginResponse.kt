package com.example.data.dto.response.login

data class LoginResponse (
    val statusCode: Int,
    val msg: String,
    val data: Data,
)