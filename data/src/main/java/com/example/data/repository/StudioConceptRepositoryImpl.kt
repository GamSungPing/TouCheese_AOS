package com.example.data.repository

import com.example.data.datasource.StudioConceptDataSource
import com.example.domain.model.StudioConcepts
import com.example.domain.repository.studio.StudioConceptRepository
import javax.inject.Inject

internal class StudioConceptRepositoryImpl @Inject constructor(
    private val studioConceptDataSource: StudioConceptDataSource
): StudioConceptRepository {
    override suspend fun getStudioConcept(): StudioConcepts {
        return studioConceptDataSource.getStudioConcept().toDomainModel()
    }
}