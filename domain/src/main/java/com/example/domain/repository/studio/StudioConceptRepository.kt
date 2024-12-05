package com.example.domain.repository.studio

import com.example.domain.model.StudioConcepts

interface StudioConceptRepository {
    suspend fun getStudioConcept(): StudioConcepts
}