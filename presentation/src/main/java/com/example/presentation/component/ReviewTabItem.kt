package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.detail.ReviewItem
import com.example.presentation.R

@Composable
fun ReviewTabItem(
    review: List<ReviewItem>,
    reviewColumnSize: Int,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        repeat(reviewColumnSize) { rowIndex ->
            Row(modifier = Modifier.fillMaxWidth()) {
                val start = rowIndex * 3
                val end = minOf(start + 3, review.size)
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