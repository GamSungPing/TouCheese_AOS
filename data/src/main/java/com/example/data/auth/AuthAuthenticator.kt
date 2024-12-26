package com.example.data.auth

import android.util.Log
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
        Log.d("dasdasdsa", "authenticate")
        val accessToken = runBlocking { getNewAccessToken() } ?: return null
        val builder = response.request.newBuilder()
        return builder
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

    private suspend fun getNewAccessToken(): String? {
        val authDataSource = authDataSource.get()
        val newToken = authDataSource.refresh().getOrNull()
        Log.d("dasdasdsa", "ds ${newToken == null}")
        return newToken?.let {
            Log.d("dasdasdsa", newToken.toString())
            tokenDataSource.saveToken(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken
            )
            it.accessToken
        } ?: run {
            Log.d("dasdasdsa", "newToken null")
            authDataSource.logout()
            null
        }
    }
}
