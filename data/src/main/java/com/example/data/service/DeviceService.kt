package com.example.data.service

import com.example.domain.model.FcmToken
import retrofit2.http.Body
import retrofit2.http.POST

internal interface DeviceService {
    @POST("device/register")
    suspend fun sendDeviceToken(@Body request: FcmToken)
}