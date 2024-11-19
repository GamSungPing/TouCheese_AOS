package com.example.data.repository

import com.example.data.datasource.StudioDataSource
import com.example.domain.model.StudioInfo
import com.example.domain.repository.studio.StudioRepository
import javax.inject.Inject

internal class StudioRepositoryImpl @Inject constructor(
    private val studioDataSource: StudioDataSource
) : StudioRepository {
    override suspend fun getStudioInfo(studioId: Int): StudioInfo {
        return studioDataSource.getStudioInfo(studioId).toDomainModel()
    }
}