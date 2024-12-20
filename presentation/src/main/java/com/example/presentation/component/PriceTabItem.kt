package com.example.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.detail.ProductItem
import com.example.presentation.util.ext.toKoreanUnit

@Composable
fun PriceTabItem(
    productItem: List<ProductItem>,
    onClickProduct: (Int, String, String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        productItem.forEach { product ->
            StudioProductItem(
                id = product.id,
                productName = product.name,
                description = product.description,
                image = product.imageUrl,
                reviewCount = product.reviewCount,
                price = product.price.toKoreanUnit(),
                reviewClickListener = {

                },
                rootClickListener = {
                    onClickProduct(product.id, product.description, product.imageUrl)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PriceTabItemPreview() {
    PriceTabItem(
        productItem = listOf(),
        onClickProduct = { _, _, _ -> }
    )
}