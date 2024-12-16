package com.example.presentation.studio.navigation

import android.net.Uri
import androidx.navigation.NavHostController

fun NavHostController.getProductId(): Int =
    this.previousBackStackEntry?.savedStateHandle?.get<Int>(PRODUCT_ID) ?: 0

fun NavHostController.getDescription(): String =
    this.previousBackStackEntry?.savedStateHandle?.get<String>(DESCRIPTION_ID) ?: ""

fun NavHostController.getProfileUrl(): String =
    this.previousBackStackEntry?.savedStateHandle?.get<String>(PROFILE_URL) ?: ""

fun NavHostController.setProductDefaultInfo(
    description: String,
    productId: Int,
    profileUrl: String
){
    this.currentBackStackEntry?.savedStateHandle?.apply {
        set(DESCRIPTION_ID, description)
        set(PRODUCT_ID, productId)
        set(PROFILE_URL, Uri.encode(profileUrl))
    }
}

const val PRODUCT_ID = "productId"
const val DESCRIPTION_ID = "description"
const val PROFILE_URL = "profileUrl"