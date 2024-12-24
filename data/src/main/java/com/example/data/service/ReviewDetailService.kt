package com.example.data.service

import com.example.data.auth.AuthType
import com.example.data.dto.response.reviewdetail.ReviewDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Tag

internal interface ReviewDetailService {

    @GET("review/studio/{studioId}/detail/{detailId}")
    suspend fun getReviewDetailByReviewId(
        @Path("studioId") productId: Int,
        @Path("detailId") detailId: Int,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): ReviewDetailResponse
}