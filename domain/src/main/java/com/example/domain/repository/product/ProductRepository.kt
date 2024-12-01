package com.example.domain.repository.product

import com.example.domain.model.Product

interface ProductRepository {
    suspend fun getProductId(productId : Int) : Product
}