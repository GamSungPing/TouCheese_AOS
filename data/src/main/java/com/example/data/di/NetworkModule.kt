package com.example.data.di

import com.example.data.service.StudioService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.example.data.BuildConfig
import com.example.data.dto.request.util.LocalDateAdapter
import com.example.data.service.ConceptService
import com.example.data.service.DeviceService
import com.example.data.service.ProductService
import com.example.data.service.ReservationService
import com.example.data.service.ReviewDetailService
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun deviceRegisterService(retrofit: Retrofit): DeviceService {
        return retrofit.create(DeviceService::class.java)
    }

    @Singleton
    @Provides
    fun provideReservationService(retrofit: Retrofit): ReservationService {
        return retrofit.create(ReservationService::class.java)
    }

    @Singleton
    @Provides
    fun provideReviewDetailService(retrofit: Retrofit): ReviewDetailService {
        return retrofit.create(ReviewDetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideConceptService(retrofit: Retrofit): ConceptService {
        return retrofit.create(ConceptService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun provideStudioService(retrofit: Retrofit): StudioService {
        return retrofit.create(StudioService::class.java)
    }

    @Singleton
    @Provides
    fun provideReservationService(retrofit: Retrofit): ReservationService {
        return retrofit.create(ReservationService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(LocalDateTime::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(LocalDateAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}