package com.example.presentation.calendar

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun CalendarInBottomSheet(
    pagerState: PagerState,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
) {
    HorizontalPager(
        state = pagerState
    ) { page ->
        val date = LocalDate.of(
            2024 + page / 12,
            page % 12 + 1,
            1
        )

        CalendarMonth(
            selectedDate = selectedDate,
            currentDate = date,
            onSelectedDate = {
                onSelectedDate(it)
            }
        )
    }
}