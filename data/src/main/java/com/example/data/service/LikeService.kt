package com.example.data.service

import com.example.data.dto.request.like.LikeRequestDTO
import com.example.data.dto.response.like.LikeResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


internal interface LikeService {

    @GET("like")
    suspend fun getLikes(
        @Query("memberId") memberId: Int) : LikeResponse

    @POST("like")
    suspend fun addLike(
        @Body request: LikeRequestDTO
    )

    @DELETE("like/delete/{studioId}")
    suspend fun deleteLike(
        @Path("studioId") studioId: Int,
        @Query("memberId") memberId: Int
    )
}