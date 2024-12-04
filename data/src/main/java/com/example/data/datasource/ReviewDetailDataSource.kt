package com.example.data.datasource

import com.example.data.service.ReviewDetailService
import com.example.domain.model.ReviewDetail
import javax.inject.Inject


internal class ReviewDetailDataSource @Inject constructor(
    private val reviewDetailService: ReviewDetailService
){
    suspend fun getReviewDetailByReviewId(studioId: Int, reviewId: Int): ReviewDetail {
        return reviewDetailService.getReviewDetailByReviewId(studioId, reviewId).toDomainModel()
    }
}