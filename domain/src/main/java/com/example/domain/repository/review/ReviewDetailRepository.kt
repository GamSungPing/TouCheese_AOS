package com.example.domain.repository.review

import com.example.domain.model.ReviewDetail

interface ReviewDetailRepository {
    suspend fun getReviewDetailByReviewId(studioId: Int, reviewId : Int) : ReviewDetail
}