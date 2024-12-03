package com.example.domain.repository.product

import com.example.domain.model.Product
import com.example.domain.model.ProductDetail

interface ProductRepository {
    suspend fun getProductDetailByProductId(productId : Int) : ProductDetail
    suspend fun getAllProductWithStudioId(studioId : Int) : List<Product>
}