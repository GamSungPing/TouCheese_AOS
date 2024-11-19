package com.example.data.service

import com.example.data.dto.response.studio.StudioInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface StudioService {
    @GET("studio/{id}")
    suspend fun getStudioInfo(@Path("id") studioId: Int): StudioInfoResponse

}