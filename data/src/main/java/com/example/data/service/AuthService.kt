package com.example.data.service

import com.example.data.auth.AuthType
import com.example.data.dto.request.LoginRequest
import com.example.data.dto.request.RefreshRequest
import com.example.data.dto.response.RefreshResponse
import com.example.data.dto.response.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag

internal interface AuthService {

    @POST("auth/login")
    suspend fun login(
        @Header("socialId") socialId: String,
        @Body body: LoginRequest,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): LoginResponse

    @POST("auth/refreshAccessToken")
    suspend fun refresh(
        @Body body: RefreshRequest,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): RefreshResponse

    @POST("auth/logout")
    suspend fun logout(
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN
    )

    @POST("auth/withdrawal")
    suspend fun withdraw(
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN
    )

    @PUT("auth/{memberId}/name")
    suspend fun modifyNickName(
        @Tag authType: AuthType = AuthType.ACCESS_TOKEN,
        @Path("memberId") memberId: Long,
        @Query("newName") newName: String
    )
}