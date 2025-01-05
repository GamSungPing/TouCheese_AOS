package com.example.data.auth

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

internal class AuthAuthenticator @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val authDataSource: Provider<AuthDataSource>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking { getNewAccessToken() } ?: return null
        val builder = response.request.newBuilder()
        return builder
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

    private suspend fun getNewAccessToken(): String? {
        val authDataSource = authDataSource.get()
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
