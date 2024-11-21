package com.example.data.repository

import com.example.data.datasource.StudioDataSource
import com.example.domain.model.StudioInfo
import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.repository.studio.StudioRepository
import com.example.domain.rule.Concept
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region
import javax.inject.Inject

internal class StudioRepositoryImpl @Inject constructor(
    private val studioDataSource: StudioDataSource
) : StudioRepository {
    override suspend fun getStudioInfo(studioId: Int): StudioInfo {
        return studioDataSource.getStudioInfo(studioId).toDomainModel()
    }

    override suspend fun getStudioOnlyConcept(
        conceptId: Int,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource.getStudioOnlyConcept(conceptId, pageNo).toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegion(
        concept: Int,
        region: List<Int>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptAndRegion(concept, region, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByLowerPrice(conceptId, priceCategory.toString(), pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByHighRating(
        conceptId: Int,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByHighRating(conceptId, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        conceptId: Int,
        region: List<Int>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptAndRegionOrderByHighRating(conceptId, region, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        conceptId: Int,
        region: List<Int>,
        priceCategory: Pricing,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptAndRegionsOrderByPrice(conceptId, region, priceCategory.toString())
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByHighRatingAndLowerPrice(
                conceptId,
                priceCategory.toString(),
                pageNo
            ).toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: Pricing,
        region: List<Int>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource.getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
            conceptId,
            priceCategory.toString(),
            region,
            pageNo
        ).toDomainModel()
    }
}