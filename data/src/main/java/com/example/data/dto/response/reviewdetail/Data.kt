package com.example.data.dto.response.reviewdetail

internal data class Data(
    val userProfileImageString: String,
    val userName: String,
    val dataString: String,
    val imageStrings: List<String>,
    val content: String,
    val rating: Int,
    val reply: List<String>
) {
}