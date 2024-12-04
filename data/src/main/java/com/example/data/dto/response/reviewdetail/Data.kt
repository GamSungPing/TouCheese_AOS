package com.example.data.dto.response.reviewdetail

import com.example.domain.model.detail.Reply

internal data class Data(
    val userProfileImageString: String?,
    val userName: String,
    val dateString: String?,
    val imageStrings: List<String>,
    val content: String?,
    val rating: Int,
    val reply: Reply
) {
}