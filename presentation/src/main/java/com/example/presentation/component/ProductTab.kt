package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.domain.model.detail.ProductItem
import com.example.domain.model.detail.ReviewItem
import com.example.presentation.studio.vm.model.TabStatus
import com.example.presentation.theme.primaryColor
import com.example.presentation.theme.secondPrimaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductTab(
    productItem: List<ProductItem>,
    review: List<ReviewItem>,
    reviewColumnSize: Int,
    onClickProduct: (Int, String, String) -> Unit,
    onClickReview: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = TabStatus.Price.value)
    val coroutineScope = rememberCoroutineScope()
    val tabs = TabStatus.entries
    val pages = tabs.map { it.title }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.Transparent,
        modifier = Modifier
            .padding(3.dp)
            .background(Color.LightGray)
    ) {
        pages.forEachIndexed { idx, title ->
            val isSelected = pagerState.currentPage == idx
            Tab(
                text = {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = 17.sp
                    )
                },
                selected = isSelected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(idx)
                    }
                },
                modifier = Modifier
                    .background(color = if (isSelected) secondPrimaryColor else primaryColor)
                    .zIndex(if (isSelected) 2f else 1f)
            )
        }
    }

    HorizontalPager(
        count = pages.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) { page ->

        when (page) {
            TabStatus.Price.value -> {
                PriceTabItem(productItem = productItem) { productId, description, path ->
                    onClickProduct(productId, description, path)
                }
            }

            TabStatus.Review.value -> {
                ReviewTabItem(
                    review = review,
                    reviewColumnSize = reviewColumnSize,
                ) { reviewId ->
                    onClickReview(reviewId)
                }
            }
        }
    }
}