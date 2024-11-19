package com.example.domain.repository.studio

import com.example.domain.model.StudioInfo

interface StudioRepository {
    suspend fun getStudioInfo(studioId: Int): StudioInfo
}