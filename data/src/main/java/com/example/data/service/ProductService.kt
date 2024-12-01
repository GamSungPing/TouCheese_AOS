package com.example.data.service

import com.example.data.dto.response.product.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductService {

    @GET("product/{id}")
    suspend fun getProductID(@Path("id") productId : Int) : ProductResponse
}