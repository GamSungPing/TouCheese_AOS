package com.example.presentation.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.presentation.R
import com.example.presentation.theme.gray01
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray10

@Composable
fun ProductDetailHeader(
    context: Context,
    path: String,
    productName: String,
    description: String
) {
    Column(
        modifier = Modifier
            .background(gray01)
            .fillMaxWidth()
            .wrapContentSize()
            .padding(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(path)
                .build(),
            contentDescription = stringResource(R.string.text_logo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(144.dp)
                .height(144.dp)
                .border(
                    width = 1.dp,
                    color = gray03,
                    shape = RoundedCornerShape(10.dp)
                )
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = productName,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            color = gray10,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .border(
                    width = 1.dp,
                    color = gray02
                )
                .padding(top = 8.dp, end = 15.dp, start = 15.dp, bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = description,
                color = gray10,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductDetailHeaderPreview() {
    ProductDetailHeader(
        context = LocalContext.current,
        path = "",
        productName = "테스트",
        description = "테스트"
    )
}
