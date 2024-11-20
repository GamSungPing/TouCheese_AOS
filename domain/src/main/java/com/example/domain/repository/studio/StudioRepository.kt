package com.example.domain.repository.studio

import com.example.domain.model.StudioInfo
import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.rule.Concept
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region

interface StudioRepository {
    suspend fun getStudioInfo(studioId: Int): StudioInfo
    suspend fun getStudioWithConcept(
        conceptId: Concept,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegion(
        concept: Concept,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByHighRating(
        conceptId: Concept,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        conceptId: Concept,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        conceptId: Concept,
        region: List<Int>,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>
}