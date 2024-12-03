package com.example.presentation.studio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.detail.ProductItem
import com.example.domain.model.detail.ReviewItem
import com.example.domain.rule.Output.Companion.reviewFormat
import com.example.domain.rule.Output.Companion.workDayFormat
import com.example.presentation.R
import com.example.presentation.component.ExpandableNotificationCard
import com.example.presentation.component.StudioImageSection
import com.example.presentation.component.StudioProductItem
import com.example.presentation.studio.model.TabStatus
import com.example.presentation.studio.sideeffect.StudioSideEffect
import com.example.presentation.studio.vm.StudioViewModel
import com.example.presentation.theme.primaryColor
import com.example.presentation.theme.secondPrimaryColor
import com.example.presentation.util.toKoreanUnit
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun StudioScreen(
    viewModel: StudioViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "Back",
                        modifier = Modifier.clickable { viewModel.onClickBackButton() }
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(state.studioLogo)
                            .build(),
                        contentDescription = stringResource(id = R.string.text_logo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = state.product.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.Share, contentDescription = "Share")
                    Icon(Icons.Default.BookmarkBorder, contentDescription = "Share")
                }
            }
            item { StudioImageSection(state.product.detailImages) }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.StarBorder, contentDescription = "Rating")
                        Text(
                            text = reviewFormat(
                                count = state.product.rating,
                                reviewCount = state.product.reviewCount
                            ), style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccessTime, contentDescription = "Operating Hours")
                        Text(
                            text = workDayFormat(
                                workTime = state.product.operatingHours,
                                holidays = state.product.holidays
                            ), modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Address")
                        Text(
                            text = state.product.address,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            item { ExpandableNotificationCard(state.product.notice) }
            item {
                Tab(
                    notice = state.product.notice,
                    productItem = state.product.productItems,
                    review = state.product.reviewItems,
                    reviewColumnSize = state.reviewColumnSize
                )
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tab(
    notice: String,
    productItem: List<ProductItem>,
    review: List<ReviewItem>,
    reviewColumnSize: Int
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
            .clip(RoundedCornerShape(16.dp))
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
                ExpandableNotificationCard(notice)

                Column(modifier = Modifier.fillMaxWidth()) {
                    productItem.forEach { product ->
                        StudioProductItem(
                            id = product.id,
                            productName = product.name,
                            description = product.description,
                            image = product.imageUrl,
                            reviewCount = product.reviewCount,
                            price = product.price.toKoreanUnit(),
                            reviewClickListener = {},
                            rootClickListener = {}
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            TabStatus.Review.value -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    repeat(reviewColumnSize) { rowIndex ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            val start = rowIndex * 3
                            val end = minOf(start + 3, review.size) // 범위를 초과하지 않도록 설정
                            review.subList(start, end).forEach { reviewItem ->
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(reviewItem.imageUrl)
                                        .build(),
                                    contentDescription = stringResource(id = R.string.text_logo),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(140.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    StudioScreen()
}