package com.example.domain.model

import com.example.domain.model.detail.Reply

data class ReviewDetail(
    val userProfileImageString: String?,
    val userName: String,
    val dateString: String,
    val imageStrings: List<String>,
    val content: String?,
    val rating: Int,
    val reply: Reply?
)