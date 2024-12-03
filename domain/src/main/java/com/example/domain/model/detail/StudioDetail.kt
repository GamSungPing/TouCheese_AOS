package com.example.domain.model.detail

data class StudioDetail(
    val id: Int,
    val name: String,
    val detailImages: List<String>,
    val rating: String,
    val reviewCount: Int,
    val operatingHours: String,
    val holidays: List<String>,
    val address: String,
    val notice: String,
    val productItems: List<ProductItem>,
    val reviewItems: List<ReviewItem>
){
    companion object{
        fun create(): StudioDetail {
            return StudioDetail(
                id = 0,
                name = "",
                detailImages = emptyList(),
                rating = "",
                reviewCount = 0,
                operatingHours = "",
                holidays = emptyList(),
                address = "",
                notice = "",
                productItems = emptyList(),
                reviewItems = emptyList()
            )
        }
    }
}
