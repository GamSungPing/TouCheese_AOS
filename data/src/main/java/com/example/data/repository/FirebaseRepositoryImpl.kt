package com.example.data.repository

import com.example.data.datasource.FirebaseDataSource
import com.example.domain.repository.FirebaseRepository
import javax.inject.Inject

internal class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) : FirebaseRepository {
    override suspend fun getFirebaseToken(): String {
        return firebaseDataSource.getTokenFromFirebase()
    }
}