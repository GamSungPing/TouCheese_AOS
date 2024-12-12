package com.example.data.repository

import com.example.data.datasource.DeviceRegisterDataSource
import com.example.domain.model.FcmToken
import com.example.domain.repository.device.DeviceRegisterRepository
import javax.inject.Inject

internal class DeviceRegisterRepositoryImpl @Inject constructor(
    private val deviceRegisterDataSource: DeviceRegisterDataSource
) : DeviceRegisterRepository {
    override suspend fun sendToToken(token: String) {
        val fcmToken = FcmToken(memberId = 2, deviceToken = token)
        deviceRegisterDataSource.sendToToken(fcmToken)
    }
}