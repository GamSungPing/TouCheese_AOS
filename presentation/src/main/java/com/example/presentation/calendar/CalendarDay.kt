package com.example.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.gray04
import com.example.presentation.theme.gray06
import com.example.presentation.theme.primary06
import java.time.LocalDate

@Composable
fun CalendarDay(
    date: LocalDate,
    isSelected: Boolean,
    isDisabled: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clickable(enabled = !isDisabled) {
                    onDateSelected(date)
                }
                .background(
                    color = when {
                        isSelected -> primary06
                        else -> Color.Transparent
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                color = when {
                    isDisabled -> gray04
                    isSelected -> Color.White
                    else -> gray06
                },
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            )
        }
    }
}

@Preview
@Composable
fun CalendarDayPreview() {
    CalendarDay(
        date = LocalDate.now(),
        isSelected = true,
        isDisabled = false,
        onDateSelected = { }
    )
}
