package com.example.presentation.calendar

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun TcCalendar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lastDay by remember { mutableIntStateOf(LocalDate.now().lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableIntStateOf(selectedDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier.fillMaxWidth().background(Color.White)) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(firstDayOfWeek + 1) {
                item {
                    Box(modifier = Modifier.size(32.dp))
                }
            }

            items(items = days) { day ->
                val date = selectedDate.withDayOfMonth(day)
                val isPast = date.isBefore(LocalDate.now())

                CalendarDay(
                    date = date,
                    isSelected = date.isEqual(selectedDate),
                    isDisabled = isPast,
                    onDateSelected = {
                        onDateSelected(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun TcCalendarPreview() {
    TcCalendar(
        selectedDate = LocalDate.now(),
        onDateSelected = { }
    )
}