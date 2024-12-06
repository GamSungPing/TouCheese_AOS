package com.example.data.di

import android.content.Context
import com.example.data.datasource.DataStoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataStoreModule {
    @Singleton
    @Provides
    fun provideDataStoreDataSource(@ApplicationContext context: Context): DataStoreDataSource {
        return DataStoreDataSource(context)
    }
}