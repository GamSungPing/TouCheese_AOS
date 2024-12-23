package com.example.data.auth

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val authDataSource: AuthDataSource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking { getNewAccessToken() } ?: return null
        return response.request.newBuilder().header("Authorization", "Bearer $accessToken").build()
    }

    private suspend fun getNewAccessToken(): String? {
        val newToken = authDataSource.refresh().getOrNull()
        return newToken?.let {
            tokenDataSource.saveToken(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken
            )
            it.accessToken
        } ?: run {
            authDataSource.logout()
            null
        }
    }
}
