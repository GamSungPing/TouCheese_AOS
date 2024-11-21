package com.example.data.dto.response.concept

import com.example.data.dto.response.concept.Data
import com.example.domain.model.StudioInfoWithConcept
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StudioConceptResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): List<StudioInfoWithConcept> {
        return data.content.map {
            StudioInfoWithConcept(
                id = it.id.toString(),
                name = it.name,
                profilePrice = it.profilePrice.toString(),
                rating = it.rating.toString(),
                portfolioUrls = it.portfolioUrls,
                profileURL = it.profileURL
            )
        }
    }
}