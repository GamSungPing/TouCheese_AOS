package com.example.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.rule.WeekOfDay
import com.example.presentation.R
import com.example.presentation.theme.gray10
import com.example.presentation.theme.primary06

@Composable
fun CalendarHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onForwardClick: () -> Unit
){
    Column(Modifier.background(Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = null,
                tint = primary06,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onBackClick() }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = gray10,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "forward",
                tint = primary06,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onForwardClick() }
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row(modifier) {
            val dayOfWeek = enumValues<WeekOfDay>().map { it.value }
            dayOfWeek.forEach {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .weight(1f),
                    text = it,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    color = gray10,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
@Preview
fun CalendarHeaderPreview() {
    CalendarHeader(
        title = "2023년 11월",
        onBackClick = {},
        onForwardClick = {}
    )
}