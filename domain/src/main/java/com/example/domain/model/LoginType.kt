package com.example.domain.model

enum class LoginType(private val type: String) {
    KAKAO("KAKAO");

    override fun toString(): String = type
}