package com.example.data.dto.request.like

import com.example.domain.model.LikeRequest

data class LikeRequestDTO(
    val createdAt: String,
    val memberId: Long,
    val studioId: Int
)

internal fun LikeRequest.toDataModel(): LikeRequestDTO {
    return LikeRequestDTO(
        createdAt = this.createdAt,
        memberId = this.memberId,
        studioId = this.studioId
    )
}