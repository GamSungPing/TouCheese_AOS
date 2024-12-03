package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun StudioProductItem(
    id: Int,
    productName: String,
    description: String,
    image: String,
    reviewCount: Int,
    price: String,
    reviewClickListener: () -> Unit,
    rootClickListener: (Int) -> Unit
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .clickable { rootClickListener(id) }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(200.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .wrapContentHeight()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Top)
                    .padding(8.dp)
                    .clickable { reviewClickListener() }
            ) {
                Text(text = productName)
                Text(text = description)
                TextButton(onClick = { /*TODO*/ }){
                    Text(text = "리뷰 $reviewCount")
                }
            }
            Text(
                text = price,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    StudioProductItem(
        id = 1,
        "증명사진",
        description = "신원 확인이 주된 목적인 사진입니다.\n" +
                "주로 공식 문서 및 신분증에 사용되는 사진으로\n" +
                "여권, 운전면허증, 학생증 등과 함께 나타납니다.",
        image = "https://via.placeholder.com/150",
        reviewCount = 108,
        price = "75,000원",
        reviewClickListener = {},
        rootClickListener = {},
    )
}