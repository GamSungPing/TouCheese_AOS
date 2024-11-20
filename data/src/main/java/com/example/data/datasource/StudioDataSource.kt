package com.example.data.datasource

import com.example.data.dto.response.concept.StudioConceptResponse
import com.example.data.dto.response.studio.StudioInfoResponse
import com.example.data.service.StudioService
import javax.inject.Inject

internal class StudioDataSource @Inject constructor(
    private val studioService: StudioService
) {
    suspend fun getStudioInfo(studioId: Int): StudioInfoResponse {
        return studioService.getStudioInfo(studioId)
    }

    suspend fun getStudioWithConcept(conceptId: Int, pageNo: Int?): StudioConceptResponse {
        return studioService.getStudioInfoWithConcept(conceptId, pageNo)
    }

    suspend fun getStudioWithConceptAndRegion(
        conceptId: Int,
        regionId: List<Int>,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService.getStudioWithConceptAndRegion(conceptId, regionId, pageNo)
    }

    suspend fun getStudioWithConceptOrderByLowerPrice(
        conceptId: Int,
        priceCategory: String,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService.getStudioWithConceptOrderByLowerPrice(conceptId, priceCategory, pageNo)
    }

    suspend fun getStudioWithConceptOrderByHighRating(
        conceptId: Int,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService.getStudioWithConceptOrderByHighRating(conceptId, pageNo)
    }

    suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        conceptId: Int,
        regionId: List<Int>,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService.getStudioWithConceptAndRegionOrderByHighRating(
            conceptId,
            regionId,
            pageNo
        )
    }

    suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        conceptId: Int,
        regionId: List<Int>,
        priceCategory: String,
    ): StudioConceptResponse {
        return studioService.getStudioWithConceptAndRegionsOrderByPrice(conceptId, regionId, priceCategory)
    }

    suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: String,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService
            .getStudioWithConceptOrderByHighRatingAndLowerPrice(conceptId, priceCategory, pageNo)
    }

    suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
        conceptId: Int,
        priceCategory: String,
        regionId: List<Int>,
        pageNo: Int?
    ): StudioConceptResponse {
        return studioService
            .getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(
                conceptId, regionId, priceCategory, pageNo
            )
    }
}