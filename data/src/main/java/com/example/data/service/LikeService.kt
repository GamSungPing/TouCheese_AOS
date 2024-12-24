package com.example.data.service

import com.example.data.auth.AuthType
import com.example.data.dto.request.like.LikeRequestDTO
import com.example.data.dto.response.like.LikeResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag


internal interface LikeService {
    @GET("like")
    suspend fun getLikes(
        @Query("memberId") memberId: Long,
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN
    ) : LikeResponse

    @POST("like")
    suspend fun addLike(
        @Body request: LikeRequestDTO,
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN
    )

    @DELETE("like/delete/{studioId}")
    suspend fun deleteLike(
        @Path("studioId") studioId: Int,
        @Query("memberId") memberId: Long,
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN
    )
}