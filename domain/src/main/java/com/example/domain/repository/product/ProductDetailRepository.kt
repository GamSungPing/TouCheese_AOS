package com.example.domain.repository.product

import com.example.domain.model.ProductDetail

interface ProductDetailRepository {
    suspend fun getProductDetailWithStudioId(studioId : Int) : List<ProductDetail>
}