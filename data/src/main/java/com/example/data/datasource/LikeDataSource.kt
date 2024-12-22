package com.example.data.datasource

import com.example.data.dto.request.like.LikeRequestDTO
import com.example.data.dto.response.like.LikeResponse
import com.example.data.service.LikeService
import javax.inject.Inject

internal class LikeDataSource @Inject constructor(
    private val likeService: LikeService
) {
    suspend fun getLikes(memberId: Int): LikeResponse {
        return likeService.getLikes(memberId)
    }

    suspend fun addLike(request: LikeRequestDTO) {
        return likeService.addLike(request)
    }
}