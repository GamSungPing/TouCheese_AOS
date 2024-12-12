package com.example.domain.usecase

import com.example.domain.repository.FirebaseRepository
import com.example.domain.repository.device.DeviceRegisterRepository
import javax.inject.Inject

class RegisterDeviceTokenUseCase @Inject constructor(
    private val registerRepository: DeviceRegisterRepository,
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke() {
        registerRepository.sendToToken(
            firebaseRepository.getFirebaseToken()
        )
    }
}