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

    override suspend fun getStudioWithConcept(
        conceptId: Concept,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource.getStudioWithConcept(conceptId.id, pageNo).toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegion(
        concept: Concept,
        region: List<Int>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptAndRegion(concept.id, region, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByLowerPrice(conceptId.id, priceCategory.toString(), pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByHighRating(
        conceptId: Concept,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByHighRating(conceptId.id, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        conceptId: Concept,
        region: List<Region>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        val regionId = region.map { it.id }
        return studioDataSource
            .getStudioWithConceptAndRegionOrderByHighRating(conceptId.id, regionId, pageNo)
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        conceptId: Concept,
        region: List<Region>,
        priceCategory: Pricing
    ): List<StudioInfoWithConcept> {
        val regionId = region.map { it.id }
        return studioDataSource
            .getStudioWithConceptAndRegionsOrderByPrice(conceptId.id, regionId, priceCategory.toString())
            .toDomainModel()
    }

    override suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        return studioDataSource
            .getStudioWithConceptOrderByHighRatingAndLowerPrice(
                conceptId.id,
                priceCategory.toString(),
                pageNo
            ).toDomainModel()
    }

    override suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
        conceptId: Concept,
        priceCategory: Pricing,
        region: List<Region>,
        pageNo: Int?
    ): List<StudioInfoWithConcept> {
        val regionId = region.map { it.id }
        return studioDataSource.getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
            conceptId.id,
            priceCategory.toString(),
            regionId,
            pageNo
        ).toDomainModel()
    }
}