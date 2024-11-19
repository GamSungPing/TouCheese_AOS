package com.example.data.datasource

import com.example.data.dto.response.studio.StudioInfoResponse
import com.example.data.service.StudioService
import javax.inject.Inject

internal class StudioDataSource @Inject constructor(
    private val studioService: StudioService
) {
    suspend fun getStudioInfo(studioId: Int): StudioInfoResponse {
        return studioService.getStudioInfo(studioId)
    }
}