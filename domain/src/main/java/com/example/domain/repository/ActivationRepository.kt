package com.example.domain.repository

import com.example.domain.model.Activation
import kotlinx.coroutines.flow.Flow

interface ActivationRepository {
    val activation: Flow<Activation>
    suspend fun saveUserActivation(active: Activation)
}