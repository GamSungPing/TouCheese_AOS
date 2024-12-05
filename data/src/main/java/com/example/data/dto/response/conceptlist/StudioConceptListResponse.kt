package com.example.data.dto.response.conceptlist

import com.example.domain.model.ConceptItem
import com.example.domain.model.StudioConcepts
import com.example.domain.rule.Concept
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StudioConceptListResponse(
    val statusCode: Int,
    val msg: String,
    val data: List<Data>
) {
    fun toDomainModel(): StudioConcepts {
        return StudioConcepts(
            data = data.map {
                ConceptItem(
                    id = Concept.from(it.id),
                    name = it.name,
                    mainUrl = it.mainUrl
                )
            }
        )
    }
}