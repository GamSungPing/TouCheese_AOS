package com.example.data.di

import android.content.Context
import com.example.data.auth.AuthAuthenticator
import com.example.data.auth.AuthInterceptor
import com.example.data.datasource.ActivationDataSource
import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.TokenDataSource
import com.example.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AuthModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenDataSource: TokenDataSource): AuthInterceptor {
        return AuthInterceptor(tokenDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthenticator(
        tokenDataSource: TokenDataSource,
        authDataSource: Provider<AuthDataSource>
    ): Authenticator {
        return AuthAuthenticator(tokenDataSource, authDataSource)
    }


    @Singleton
    @Provides
    fun provideAuthDataSource(
        @ApplicationContext context: Context,
        authService: AuthService
    ): AuthDataSource {
        return AuthDataSource(context, authService)
    }

    @Singleton
    @Provides
    fun provideTokenDataSource(
        @ApplicationContext context: Context
    ): TokenDataSource {
        return TokenDataSource(context)
    }

    @Singleton
    @Provides
    fun provideFirstLogInDataSource(
        @ApplicationContext context: Context
    ): ActivationDataSource = ActivationDataSource(context)
}