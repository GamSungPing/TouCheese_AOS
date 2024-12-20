package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.presentation.R
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray06

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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(max = 147.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = gray03,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top=8.dp, start = 8.dp)
                .clickable { rootClickListener(id) }
        ) {
            Text(
                text = productName,
                fontFamily = FontFamily(Font(R.font.pretendard_bold))
            )
            Text(
                text = description,
                color = gray06,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(top = 4.dp)
            )
            ReviewButton(
                reviewCount = reviewCount,
                onClick = reviewClickListener
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = price,
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                modifier = Modifier.padding(bottom = 10.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
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