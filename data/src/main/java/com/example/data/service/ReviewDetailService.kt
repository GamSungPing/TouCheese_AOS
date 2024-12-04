package com.example.data.service

import com.example.data.dto.response.reviewdetail.ReviewDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ReviewDetailService {

    @GET("review/studio/{studioId}/detail/{detailId}")
    suspend fun getReviewDetailByReviewId(
        @Path("studioId") productId: Int,
        @Path("detailId") detailId: Int
    ): ReviewDetailResponse
}