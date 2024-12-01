package com.example.data.repository

import com.example.data.datasource.ProductDataSource
import com.example.domain.model.Product
import com.example.domain.repository.product.ProductRepository
import javax.inject.Inject


internal class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProductId(productId: Int): Product {
        return productDataSource.getProductId(productId).toDomainModel()
    }
}