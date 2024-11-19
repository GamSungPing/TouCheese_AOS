package com.example.data.di

import com.example.data.repository.StudioRepositoryImpl
import com.example.domain.repository.studio.StudioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindStudioRepository(studioRepositoryImpl: StudioRepositoryImpl): StudioRepository
}