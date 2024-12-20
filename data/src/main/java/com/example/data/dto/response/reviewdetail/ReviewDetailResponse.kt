package com.example.data.dto.response.reviewdetail

import com.example.domain.model.ReviewDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ReviewDetailResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): ReviewDetail {
        return ReviewDetail(
            userProfileImageString = data.userProfileImageString ?: "",
            userName = data.userName,
            dateString = data.dateString ?: "",
            imageStrings = data.imageStrings,
            content = data.content ?: "",
            rating = data.rating,
            reply = data.reply ?: null
        )
    }
}