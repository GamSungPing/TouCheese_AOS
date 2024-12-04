package com.example.data.repository

import com.example.data.datasource.ReviewDetailDataSource
import com.example.domain.model.ReviewDetail
import com.example.domain.repository.review.ReviewDetailRepository
import javax.inject.Inject


internal class ReviewDetailRepositoryImpl @Inject constructor(
    private val reviewDetailDataSource: ReviewDetailDataSource
) : ReviewDetailRepository {
    override suspend fun getReviewDetailByReviewId(studioId: Int, reviewId: Int): ReviewDetail {
        return reviewDetailDataSource.getReviewDetailByReviewId(studioId, reviewId)
    }
}
