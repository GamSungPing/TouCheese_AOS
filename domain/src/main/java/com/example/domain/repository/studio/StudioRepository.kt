package com.example.domain.repository.studio

import com.example.domain.model.StudioInfo
import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.rule.Concept
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region

interface StudioRepository {

    suspend fun getStudioInfo(studioId: Int): StudioInfo
    suspend fun getStudioOnlyConcept(
        conceptId: Int,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegion(
        concept: Int,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByHighRating(
        conceptId: Int,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        conceptId: Int,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        conceptId: Int,
        region: List<Int>,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>

    suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        region: List<Int>,
        pageNo: Int? = null
    ): List<StudioInfoWithConcept>
}