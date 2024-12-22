package com.example.data.mapper

import com.example.data.dto.request.like.LikeRequestDTO
import com.example.domain.model.LikeRequest

interface LikeMapper {
    fun toDataModel(domainModel: LikeRequest): LikeRequestDTO
}