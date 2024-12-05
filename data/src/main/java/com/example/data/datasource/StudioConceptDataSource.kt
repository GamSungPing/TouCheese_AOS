package com.example.data.datasource

import com.example.data.dto.response.conceptlist.StudioConceptListResponse
import com.example.data.service.ConceptService
import javax.inject.Inject

internal class StudioConceptDataSource @Inject constructor(
    private val studioConcept: ConceptService
) {
    suspend fun getStudioConcept(): StudioConceptListResponse {
        return studioConcept.getConcept()
    }
}