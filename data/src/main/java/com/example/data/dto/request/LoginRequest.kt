package com.example.data.dto.request

import com.example.domain.model.LoginType

data class LoginRequest(
    val socialType: String = LoginType.KAKAO.toString()
)