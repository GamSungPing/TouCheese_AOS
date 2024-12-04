package com.example.data.dto.response.reviewdetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Reply(
    val id: Int,
    val studioName: String,
    val dateString: String,
    val content: String
) {
}