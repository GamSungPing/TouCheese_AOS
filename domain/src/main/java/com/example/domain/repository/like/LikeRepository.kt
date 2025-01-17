package com.example.domain.repository.like

import com.example.domain.model.Like
import com.example.domain.model.LikeRequest

interface LikeRepository {
    suspend fun getLikes(memberId: Long): List<Like>
    suspend fun addLike(request: LikeRequest)
    suspend fun deleteLike(studioId: Int, memberId: Long)
}