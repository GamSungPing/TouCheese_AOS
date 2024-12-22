package com.example.data.repository

import com.example.data.datasource.LikeDataSource
import com.example.data.mapper.LikeMapper
import com.example.domain.model.Like
import com.example.domain.model.LikeRequest
import com.example.domain.repository.like.LikeRepository
import javax.inject.Inject

internal class LikeRepositoryImpl @Inject constructor(
    private val likeDataSource: LikeDataSource,
    private val likeMapper: LikeMapper
) : LikeRepository {
    override suspend fun getLikes(memberId: Int): List<Like> {
        return likeDataSource.getLikes(memberId).toDomainModel()
    }

    override suspend fun addLike(request: LikeRequest) {
        val likeRequestDTO = likeMapper.toDataModel(request)
        return likeDataSource.addLike(likeRequestDTO)
    }
}