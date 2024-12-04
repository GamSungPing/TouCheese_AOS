package com.example.domain.model

data class ReviewDetail (
    val userProfileImageString: String,
    val userName: String,
    val dataString: String,
    val imageStrings: List<String>,
    val content: String,
    val rating: Int,
    val reply: List<String>
) {
}