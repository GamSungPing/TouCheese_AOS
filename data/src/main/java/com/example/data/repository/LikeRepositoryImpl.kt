package com.example.data.repository

import com.example.data.datasource.LikeDataSource
import com.example.data.dto.request.like.toDataModel
import com.example.domain.model.Like
import com.example.domain.model.LikeRequest
import com.example.domain.repository.like.LikeRepository
import javax.inject.Inject

internal class LikeRepositoryImpl @Inject constructor(
    private val likeDataSource: LikeDataSource,
) : LikeRepository {
    override suspend fun getLikes(memberId: Int): List<Like> {
        return likeDataSource.getLikes(memberId).toDomainModel()
    }

    override suspend fun addLike(request: LikeRequest) {
        val likeRequestDTO = request.toDataModel()
        return likeDataSource.addLike(likeRequestDTO)
    }

    override suspend fun deleteLike(studioId: Int, memberId: Int) {
        return likeDataSource.deleteLike(studioId, memberId)
    }
}