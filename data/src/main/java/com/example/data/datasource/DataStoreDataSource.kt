package com.example.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.data.database.AppSettings
import com.example.data.database.dataStore
import com.example.data.database.entity.DefaultUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DataStoreDataSource @Inject constructor(
    private val context: Context
) {
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    val defaultInfo: Flow<DefaultUserInfo>
        get() = data.map { it.info }

    suspend fun saveDefaultInfo(email: String, phone: String) {
        dataStore.updateData {
            it.copy(
                info = it.info.copy(email = email, phone = phone)
            )
        }
    }
}