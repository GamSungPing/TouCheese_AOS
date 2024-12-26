package com.example.data.datasource

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import com.example.data.database.AppSettings
import com.example.data.database.dataStore
import com.example.data.dto.request.LoginRequest
import com.example.data.dto.request.RefreshRequest
import com.example.data.dto.response.RefreshResponse
import com.example.data.dto.response.login.LoginResponse
import com.example.data.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authService: AuthService
) {
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    val memberId: Flow<Long> = data.map { it.memberId }
    val memberName: Flow<String> = data.map { it.memberName }

    suspend fun login(socialId: String, request: LoginRequest): LoginResponse {
        return authService.login(socialId, request)
    }

    suspend fun refresh(): Result<RefreshResponse?> = runCatching {
        val accessToken = data.map { it.accessToken }.first()
        val refreshToken = data.map { it.refreshToken }.first()
        if (refreshToken.isNotBlank()) {
            authService.refresh(
                RefreshRequest(
                    accessToken = "Bearer $accessToken",
                    refreshToken = "Bearer $refreshToken",
                )
            )
        } else {
            null
        }
    }

    suspend fun logout() {
        authService.logout()
        localLogout()
    }

    suspend fun withdraw() {
        authService.withdraw()
        localLogout()
    }

    private suspend fun localLogout() {
        Log.d("dasdasdsa", "localLogout")
        dataStore.updateData {
            it.copy(
                accessToken = "",
                refreshToken = "",
                memberId = 0L,
                memberName = ""
            )
        }
    }

    suspend fun modifyNickName( newName: String) {
        val memberId = data.map { it.memberId }.first()
        dataStore.updateData {
            it.copy(memberName = newName)
        }
        authService.modifyNickName(memberId = memberId, newName = newName)
    }
}