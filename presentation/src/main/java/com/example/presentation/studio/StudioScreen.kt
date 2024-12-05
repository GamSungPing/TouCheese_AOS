package com.example.presentation.studio

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
import com.example.domain.rule.Output.Companion.reviewFormat
import com.example.domain.rule.Output.Companion.workDayFormat
import com.example.presentation.R
import com.example.presentation.component.ExpandableNotificationCard
import com.example.presentation.component.ProductTab
import com.example.presentation.component.StudioImageSection
import com.example.presentation.studio.vm.StudioViewModel

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
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = stringResource(R.string.text_back),
                        modifier = Modifier.clickable { viewModel.onClickBackButton() }
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

            state.product.notice
                ?.let {
                    item { ExpandableNotificationCard(it) }
                }

            item {
                ProductTab(
                    productItem = state.product.productItems,
                    review = state.product.reviewItems,
                    reviewColumnSize = state.reviewColumnSize,
                    onClickProduct = { id, description, path ->
                        viewModel.onClickProduct(id, description, path)
                    },
                    onClickReview = { reviewId ->
                        viewModel.onClickReview(reviewId)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    StudioScreen()
}