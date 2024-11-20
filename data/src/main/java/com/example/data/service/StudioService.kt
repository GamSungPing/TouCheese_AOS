package com.example.data.service

import com.example.data.dto.response.concept.StudioConceptResponse
import com.example.data.dto.response.studio.StudioInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 함수 공통 메소드 정보
 * @param conceptId 컨셉 고유 ID
 * @param page 페이지 번호
 * */
internal interface StudioService {
    /**
     * 스튜디오 던건 정보
     * @param 스튜디오 고유 ID
     * */
    @GET("{id}")
    suspend fun getStudioInfo(@Path("id") studioId: Int): StudioInfoResponse

    /**
     * 스튜디오 컨셉별 목록 조회
     * */
    @GET("concept/{conceptId}")
    suspend fun getStudioInfoWithConcept(
        @Path("conceptId") conceptId: Int,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 스튜디오 컨셉 + 지역 필터링
     * */
    @GET("concept/{conceptId}")
    suspend fun getStudioWithConceptAndRegion(
        @Path("conceptId") conceptId: Int,
        @Query("regionIds") regionId: List<Int>,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉 + 가격 필터링 조회
     * @param priceCategory 가격 필터
     * */
    @GET("concept/{conceptId}/low-pricing")
    suspend fun getStudioWithConceptOrderByLowerPrice(
        @Path("conceptId") conceptId: Int,
        @Query("priceCategory") priceCategory: String,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉별 인기순 필터링 조회
     * */
    @GET("concept/{conceptId}/high-rating")
    suspend fun getStudioWithConceptOrderByHighRating(
        @Path("conceptId") conceptId: Int,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉, 지역️, 인기 필터링 조회
     * @param regionIds 지역 고유 ID
     * */
    @GET("concept/{conceptId}/high-rating/regions")
    suspend fun getStudioWithConceptAndRegionOrderByHighRating(
        @Path("conceptId") conceptId: Int,
        @Query("regionIds") regionId: List<Int>,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉, 지역, 가격 필터링 조회
     *
     */
    @GET("concept/{conceptId}/regions/low-pricing")
    suspend fun getStudioWithConceptAndRegionsOrderByPrice(
        @Path("conceptId") conceptId: Int,
        @Query("regionIds") regionId: List<Int>,
        @Query("priceCategory") priceCategory: String,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉,인기,가격 필터링 조회
     * */
    @GET("concept/{conceptId}/high-rating/low-pricing")
    suspend fun getStudioWithConceptOrderByHighRatingAndLowerPrice  (
        @Path("conceptId") conceptId: Int,
        @Query("priceCategory") priceCategory: String,
        @Query("page") page: Int? = null
    ): StudioConceptResponse

    /**
     * 컨셉,지역,인기,가격 필터링 조회
     * */
    @GET("concept/{conceptId}/high-rating/regions/low-pricing")
    suspend fun getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice  (
        @Path("conceptId") conceptId: Int,
        @Query("regionIds") regionId: List<Int>,
        @Query("priceCategory") priceCategory: String,
        @Query("page") page: Int? = null
    ): StudioConceptResponse
}