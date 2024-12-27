package com.example.data.repository

import com.example.data.datasource.ActivationDataSource
import com.example.domain.model.Activation
import com.example.domain.repository.ActivationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ActivationRepositoryImpl @Inject constructor(
    private val activationDataSource: ActivationDataSource,
): ActivationRepository {

    override val activation: Flow<Activation>
        get() = activationDataSource.activation

    override suspend fun saveUserActivation(active: Activation) {
        activationDataSource.saveUserActivation(active)
    }
}