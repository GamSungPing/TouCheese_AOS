package com.example.data.service

import com.example.data.dto.response.reviewdetail.ReviewDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ReviewDetailService {

    @GET("review/studio/{studioId}")
    suspend fun getReviewDetailByReviewId(
        @Path("studioId") productId : Int,
        @Query("reviewId") reviewId: Int,
    ) : ReviewDetailResponse
}