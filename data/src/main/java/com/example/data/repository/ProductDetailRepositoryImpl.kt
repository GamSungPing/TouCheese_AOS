package com.example.data.repository

import com.example.data.datasource.ProductDataSource
import com.example.domain.model.ProductDetail
import com.example.domain.repository.product.ProductDetailRepository
import javax.inject.Inject

internal class ProductDetailRepositoryImpl @Inject constructor(
    private val productDeDataSource: ProductDataSource
) : ProductDetailRepository {
    override suspend fun getProductDetailWithStudioId(studioId: Int): List<ProductDetail> {
       return productDeDataSource.getProductDetailWithStudioId(studioId).toDomainModel()
    }
}