package com.example.data.service

import com.example.data.auth.AuthType
import com.example.data.dto.response.product.ProductResponse
import com.example.data.dto.response.productdetail.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Tag

internal interface ProductService {

    @GET("product/{id}")
    suspend fun getProductDetailByProductId(
        @Path("id") productId: Int,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): ProductDetailResponse

    @GET("product/studio/{studioId}")
    suspend fun getAllProductWithStudioId(
        @Path("studioId") studioId: Int,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): ProductResponse
}