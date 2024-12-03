package com.example.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.DarkGray,
    unSelectedColor: Color = Color.White,
    dotSize: Dp
){
    LazyRow(
        modifier = modifier
            .wrapContentSize()
            .wrapContentHeight()
    ) {
        items(totalDots){ idx ->
            IndicatorDot(
                color = if (idx == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )
            if (idx != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}