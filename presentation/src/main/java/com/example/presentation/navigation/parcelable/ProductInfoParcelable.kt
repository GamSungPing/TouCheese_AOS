package com.example.presentation.navigation.parcelable

import android.os.Parcelable
import androidx.navigation.NavHostController
import com.example.presentation.navigation.parcelable.ProductInfoParcelable.Companion.PRODUCT_INFO_PARCELABLE
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfoParcelable(
    val studioId: Int,
    val studioName: String,
    val address: String,
    val description: String,
    val productId: Int,
    val profileUrl: String
) : Parcelable {
    companion object {
        const val PRODUCT_INFO_PARCELABLE = "productInfoParcelable"
    }
}

fun NavHostController.setProductInfoParcelable(productInfo: ProductInfoParcelable) {
    this.currentBackStackEntry?.savedStateHandle?.apply {
        set(PRODUCT_INFO_PARCELABLE, productInfo)
    }
}

fun NavHostController.getProductInfoParcelable(): ProductInfoParcelable {
    return this.previousBackStackEntry?.savedStateHandle?.get<ProductInfoParcelable>(
        PRODUCT_INFO_PARCELABLE
    ) ?: ProductInfoParcelable(0, "", "", "0", 0, "")
}