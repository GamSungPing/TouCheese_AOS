package com.example.data.service

import com.example.data.auth.AuthType
import com.example.data.dto.response.conceptlist.StudioConceptListResponse
import retrofit2.http.GET
import retrofit2.http.Tag

internal interface ConceptService {

    @GET("concepts")
    suspend fun getConcept(
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): StudioConceptListResponse
}