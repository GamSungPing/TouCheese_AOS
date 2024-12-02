package com.example.data.repository

import com.example.data.datasource.ProductDataSource
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.repository.product.ProductRepository
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProductDetailByProductId(productId: Int): ProductDetail {
        return productDataSource.getProductDetailByProductId(productId)
    }
    override suspend fun getAllProductWithStudioId(studioId: Int): List<Product> {
        return productDataSource.getAllProductWithStudioId(studioId)
    }
}