package com.example.data.service

import com.example.data.dto.response.conceptlist.StudioConceptListResponse
import retrofit2.http.GET

internal interface ConceptService {

    @GET("concepts")
    suspend fun getConcept(): StudioConceptListResponse
}