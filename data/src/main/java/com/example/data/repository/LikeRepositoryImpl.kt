package com.example.data.repository

import com.example.data.datasource.LikeDataSource
import com.example.data.dto.request.like.toDataModel
import com.example.domain.model.Like
import com.example.domain.model.LikeRequest
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.like.LikeRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class LikeRepositoryImpl @Inject constructor(
    private val likeDataSource: LikeDataSource
) : LikeRepository {

    override suspend fun getLikes(memberId: Long): List<Like> {
        return likeDataSource.getLikes(memberId).toDomainModel()
    }

    override suspend fun addLike(request: LikeRequest) {
        val likeRequestDTO = request.toDataModel()
        return likeDataSource.addLike(likeRequestDTO)
    }

    override suspend fun deleteLike(studioId: Int, memberId: Long) {
        return likeDataSource.deleteLike(studioId, memberId)
    }
}