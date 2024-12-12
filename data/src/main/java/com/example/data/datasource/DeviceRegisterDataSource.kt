package com.example.data.datasource

import com.example.data.service.DeviceService
import com.example.domain.model.FcmToken
import javax.inject.Inject

internal class DeviceRegisterDataSource @Inject constructor(
    private val deviceRegisterService: DeviceService
) {
    suspend fun sendToToken(token: FcmToken) {
        return deviceRegisterService.sendDeviceToken(token)
    }
}