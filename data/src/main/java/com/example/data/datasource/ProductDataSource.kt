package com.example.data.datasource

import com.example.data.service.ProductService
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import javax.inject.Inject


internal class ProductDataSource @Inject constructor(
    private val productService: ProductService
){
    suspend fun getProductDetailByProductId(productId: Int): ProductDetail {
        return productService.getProductDetailByProductId(productId).toDomainModel()
    }

    suspend fun getAllProductWithStudioId(studioId : Int) : List<Product> {
        return productService.getAllProductWithStudioId(studioId).toDomainModel()
    }
}