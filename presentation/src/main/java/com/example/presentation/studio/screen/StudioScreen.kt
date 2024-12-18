package com.example.presentation.studio.screen

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.detail.ProductItem
import com.example.domain.model.detail.StudioDetail
import com.example.domain.rule.Output.Companion.reviewFormat
import com.example.domain.rule.Output.Companion.workDayFormat
import com.example.presentation.R
import com.example.presentation.component.ExpandableNotificationCard
import com.example.presentation.component.ProductTab
import com.example.presentation.component.StudioImageSection
import com.example.presentation.studio.navigation.parcelable.ProductInfoParcelable
import com.example.presentation.studio.vm.StudioViewModel
import com.example.presentation.studio.vm.model.StudioState

@Composable
fun StudioScreen(
    studioId: String,
    profileURL: String,
    navigateToDetail: (ProductInfoParcelable) -> Unit,
    navigateToBackStack: () -> Unit,
    viewModel: StudioViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(studioId, profileURL) {
        viewModel.setStudioInfo(studioId, profileURL)
        viewModel.load(studioId, profileURL)
    }

    StudioScreen(
        context = context,
        state = state,
        onClickBack = navigateToBackStack,
        navigateToDetail = navigateToDetail
    )
}

@Composable
fun StudioScreen(
    context: Context,
    state: StudioState,
    onClickBack: () -> Unit,
    navigateToDetail: (ProductInfoParcelable) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = stringResource(R.string.text_back),
                        modifier = Modifier.clickable { onClickBack() }
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(state.studioLogo)
                            .build(),
                        contentDescription = stringResource(R.string.text_logo),
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
                    Icon(
                        Icons.Default.Share,
                        contentDescription = stringResource(R.string.text_share)
                    )
                    Icon(
                        Icons.Default.BookmarkBorder,
                        contentDescription = stringResource(R.string.text_bookmark)
                    )
                }
            }
            item { StudioImageSection(state.product.detailImages) }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.StarBorder,
                            contentDescription = stringResource(R.string.text_rating)
                        )
                        Text(
                            text = reviewFormat(
                                count = state.product.rating,
                                reviewCount = state.product.reviewCount
                            ),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = stringResource(R.string.text_work_time)
                        )
                        Text(
                            text = workDayFormat(
                                workTime = state.product.operatingHours,
                                holidays = state.product.holidays
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = stringResource(R.string.text_addr)
                        )
                        Text(
                            text = state.product.address,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            state.product.notice?.let {
                item { ExpandableNotificationCard(it) }
            }

            item {
                ProductTab(
                    productItem = state.product.productItems,
                    review = state.product.reviewItems,
                    reviewColumnSize = state.reviewColumnSize,
                    onClickProduct = { id, description, path ->
                        navigateToDetail(
                            ProductInfoParcelable(
                                studioId = state.studioId.toInt(),
                                studioName = state.product.name,
                                address = state.product.address,
                                description = description,
                                productId = id,
                                profileUrl = path
                            )
                        )
                    },
                    onClickReview = { reviewId ->

                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    StudioScreen(
        context = LocalContext.current,
        state = StudioState(
            product = StudioDetail(
                id = 1,
                name = "스튜디오 이름",
                detailImages = listOf(),
                rating = "5",
                reviewCount = 10,
                operatingHours = "10:00",
                holidays = listOf(),
                notice = "공지사항",
                productItems = listOf(
                    ProductItem(
                        id = 1,
                        name = "상품명",
                        price = 250000,
                        reviewCount = 10,
                        description = "상품 설명",
                        isGroup = true,
                        imageUrl = ""
                    )
                ),
                reviewItems = listOf(),
                address = "경기도 수원시 팔달구 매향동",
            ),
            studioId = "1",
            profileURL = "",
            studioLogo = ""
        ),
        onClickBack = {},
        navigateToDetail = {}
    )
}