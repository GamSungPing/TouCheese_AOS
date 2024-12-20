package com.example.presentation.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.ProductOption
import com.example.presentation.R
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray08
import com.example.presentation.util.ext.toKoreanUnit

@Composable
fun ReservationProductCard(
    context: Context,
    imagePath: String,
    productName: String,
    productPrice: String,
    options: Set<ProductOption>,
    addPeopleCount: Int?
) {
    Spacer(
        modifier = Modifier
            .background(color = gray02)
            .fillMaxWidth()
            .height(5.dp)
    )

    Row(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = gray03,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imagePath)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = gray03,
                    shape = RoundedCornerShape(10.dp)
                )
        )

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = productName,
                    color = gray08,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = productPrice,
                    color = gray08,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

            Column(modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 5.dp)
            ) {
                options.forEach { option ->
                    OptionLine(option.name, option.price.toKoreanUnit())
                }
                addPeopleCount?.let {
                    if (it > 0) OptionLine("추가 인원", "${it}인")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReservationProductCardPreview() {
    ReservationProductCard(
        context = LocalContext.current,
        imagePath = "",
        productName = "증명사진",
        productPrice = "250,000원",
        options = setOf(
            ProductOption("보정 사진 추가", 60000),
        ),
        addPeopleCount = 1
    )
}
