package com.example.data.dto.response.studio

import com.example.domain.model.StudioInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StudioInfoResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): StudioInfo {
        return StudioInfo(
            id = data.id.toString(),
            name = data.name,
            profilePrice = data.profilePrice.toString(),
            rating = data.rating.toString(),
            region = data.region.name
        )
    }
}