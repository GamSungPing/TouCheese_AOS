package com.example.data.dto.response.like

import com.example.domain.model.Like
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LikeResponse(
    val data: List<Data>,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): List<Like> {
        return data.map {
            Like(
                id = it.id,
                name = it.name,
                profilePrice = it.profilePrice,
                rating = it.rating,
                portfolioUrls = it.portfolioUrls,
                profileURL = it.profileURL,
                reviewCount = it.reviewCount
            )
        }
    }
}