package com.example.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun CalendarMonth(
    selectedDate: LocalDate,
    currentDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val lastDay by remember { mutableIntStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableIntStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 1 until firstDayOfWeek + 1) {
                item {
                    Box(modifier = Modifier.size(32.dp))
                }
            }

            items(items = days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isPast = date.isBefore(LocalDate.now())

                CalendarDay(
                    date = date,
                    isSelected = date.isEqual(selectedDate),
                    isDisabled = isPast,
                    onDateSelected = {
                        onSelectedDate(it)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun CalendarMonthPreview() {
    CalendarMonth(
        selectedDate = LocalDate.now(),
        currentDate = LocalDate.now(),
        onSelectedDate = {}
    )
}