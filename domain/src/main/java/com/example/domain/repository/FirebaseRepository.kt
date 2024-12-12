package com.example.domain.repository

interface FirebaseRepository {
    suspend fun getFirebaseToken(): String
}