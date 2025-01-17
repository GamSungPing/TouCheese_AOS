package com.example.presentation.screen.studio.vm.model

import com.example.domain.model.detail.StudioDetail

data class StudioState(
    val studioId: String,
    val profileURL: String,
    val product: StudioDetail,
    val studioLogo: String,
) {
    val reviewColumnSize: Int
        get() = if (product.reviewCount % 3 == 0) {
            product.reviewCount / 3
        } else {
            product.reviewCount / 3 + 1
        }

    companion object {
        fun create(): StudioState {
            return StudioState(
                "",
                "",
                StudioDetail.create(),
                "",
            )
        }
    }
}
