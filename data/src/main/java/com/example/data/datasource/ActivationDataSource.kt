package com.example.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.data.database.AppSettings
import com.example.data.database.dataStore
import com.example.domain.model.Activation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ActivationDataSource @Inject constructor(
    private val context: Context
){
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    val activation get() = data.map { it.activation }

    suspend fun saveUserActivation(active: Activation) {
        dataStore.updateData {
            it.copy(
                activation = active
            )
        }
    }
}