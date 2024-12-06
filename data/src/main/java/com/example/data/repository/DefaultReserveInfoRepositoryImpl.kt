package com.example.data.repository

import com.example.data.datasource.DataStoreDataSource
import com.example.domain.model.DefaultReserveInfo
import com.example.domain.repository.DefaultReserveInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultReserveInfoRepositoryImpl @Inject constructor(
    private val dataSource: DataStoreDataSource
) : DefaultReserveInfoRepository {

    override val info: Flow<DefaultReserveInfo> = dataSource.defaultInfo.map { it.toDomainModel() }

    override suspend fun saveDefaultReserveInfo(email: String, phone: String) {
        dataSource.saveDefaultInfo(email, phone)
    }
}