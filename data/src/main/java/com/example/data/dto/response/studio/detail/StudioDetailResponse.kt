package com.example.data.dto.response.studio.detail

import com.example.data.mapper.formatOperatingHours
import com.example.domain.model.detail.ProductItem
import com.example.domain.model.detail.ReviewItem
import com.example.domain.model.detail.StudioDetail
import com.example.domain.rule.WeekOfDay
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudioDetailResponse(
    val statusCode: Int,
    val msg: String,
    val data: StudioData
){
    fun toDomainModel(): StudioDetail {
        with(data) {
            return StudioDetail(
                id = studioId,
                name = name,
                detailImages = detailImageStrings,
                rating = rating.toString(),
                reviewCount = reviewCount,
                operatingHours = formatOperatingHours(
                    openTime = openTime,
                    closeTime = closeTime
                ),
                holidays = holidays.map { WeekOfDay.of(it).value },
                address = address,
                notice = notice.ifBlank { null },
                productItems = products.map { product ->
                    ProductItem(
                        id = product.id,
                        name = product.name,
                        description = product.description,
                        imageUrl = product.imageString,
                        reviewCount = product.reviewCnt,
                        price = product.price,
                        isGroup = product.isGroup
                    )
                },
                reviewItems = reviews.content.map { review ->
                    ReviewItem(
                        id = review.reviewId,
                        imageUrl = review.imageUrl
                    )
                }
            )
        }
    }
}