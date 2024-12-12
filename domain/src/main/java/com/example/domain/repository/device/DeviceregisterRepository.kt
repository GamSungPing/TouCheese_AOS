package com.example.domain.repository.device

interface DeviceRegisterRepository {
   suspend fun sendToToken(token: String)
}