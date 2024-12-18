package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.rule.Grouping
import com.example.presentation.R
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray10

@Composable
fun GroupingBox(
    isGroup: Grouping,
    basePeopleAmount: Int,
    productPrice: String,
    onClickAdd: () -> Unit,
    onClickSub: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 20.dp)
    ) {
        when (isGroup) {
            Grouping.GROUP -> {
                GroupOption(
                    basePeopleAmount,
                    onClickAdd = { onClickAdd() },
                    onClickSub = { onClickSub() }
                )
            }

            Grouping.NOT_GROUP -> {
                Spacer(modifier = Modifier.height(1.dp))
            }
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(gray03)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "총 결제 금액",
                color = gray10,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 20.sp
            )
            Text(
                text = productPrice,
                color = gray10,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GroupingBoxPreview() {
    GroupingBox(
        Grouping.GROUP,
        1,
        "17,000원",
        onClickAdd = {},
        onClickSub = {}
    )
}