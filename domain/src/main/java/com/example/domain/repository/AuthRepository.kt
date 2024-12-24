package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(socialId: String): Result<Unit>
    suspend fun logout()
    suspend fun withdraw()
    suspend fun modifyNickName(newName: String)

    val loggedIn: Flow<Boolean>
    val memberId: Flow<Long>
    val memberName: Flow<String>
}