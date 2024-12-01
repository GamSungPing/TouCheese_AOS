package com.example.data.datasource

import com.example.data.dto.response.product.ProductResponse
import com.example.data.dto.response.productdetail.ProductDetailResponse
import com.example.data.service.ProductService
import javax.inject.Inject


internal class ProductDataSource @Inject constructor(
    private val productService: ProductService
){
    suspend fun getProductId(productId: Int): ProductResponse {
        return productService.getProductID(productId)
    }

    suspend fun getProductDetailWithStudioId(studioId : Int) : ProductDetailResponse {
        return productService.getProductDetailWithStudioId(studioId)
    }
}