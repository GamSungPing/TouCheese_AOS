package com.example.domain.repository

import com.example.domain.model.DefaultReserveInfo
import kotlinx.coroutines.flow.Flow

interface DefaultReserveInfoRepository {
    val info: Flow<DefaultReserveInfo>

    suspend fun saveDefaultReserveInfo(email: String, phone: String)
}