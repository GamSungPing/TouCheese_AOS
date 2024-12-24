package com.example.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.data.database.AppSettings
import com.example.data.database.dataStore
import com.example.data.dto.response.login.LoginResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TokenDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    val loggedIn: Flow<Boolean>
        get() = data.map { it.accessToken.isNotBlank() }

    suspend fun getAccessToken(): String = data.first().accessToken

    suspend fun saveToken(accessToken: String, refreshToken: String){
        dataStore.updateData {
            it.copy(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }

    suspend fun saveUseData(
        accessToken: String,
        refreshToken: String,
        memberId: Long,
        memberName: String
    ) {
        dataStore.updateData {
            it.copy(
                accessToken = accessToken,
                refreshToken = refreshToken,
                memberId = memberId,
                memberName = memberName
            )
        }
    }
}
