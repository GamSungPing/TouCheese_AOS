package com.example.data.repository

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.TokenDataSource
import com.example.data.dto.request.LoginRequest
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override val loggedIn: Flow<Boolean>
        get() = tokenDataSource.loggedIn

    override val memberId: Flow<Long>
        get() = authDataSource.memberId

    override val memberName: Flow<String>
        get() = authDataSource.memberName

    override suspend fun login(socialId: String):Result<Unit> = kotlin.runCatching{
        val request = LoginRequest()
        val response = authDataSource.login(socialId, request)
        tokenDataSource.saveUserData(
            response.data.accessToken,
            response.data.refreshToken,
            response.data.memberId,
            response.data.memberName,
        )
    }

    override suspend fun logout() {
        authDataSource.logout()
    }

    override suspend fun withdraw(){
        authDataSource.withdraw()
    }

    override suspend fun modifyNickName(newName: String) {
        authDataSource.modifyNickName(newName)

    }
}