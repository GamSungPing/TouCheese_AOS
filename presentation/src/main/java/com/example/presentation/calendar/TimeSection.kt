package com.example.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AvailableTime
import com.example.presentation.R
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray04
import com.example.presentation.theme.gray09
import com.example.presentation.theme.primary06

@Composable
fun TimeSection(
    title: String,
    slots: List<AvailableTime>,
    selectedTime: String?,
    onTimeSelected: (AvailableTime) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            fontSize = 17.sp,
            color = gray09,
            modifier = Modifier
                .padding(bottom = 8.dp)

        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.heightIn(min = 80.dp)
        ) {
            items(slots) { item ->
                val formattedTime = item.value.substring(0, 5)
                val isSelected = item.value == selectedTime

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = if (isSelected) primary06 else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Color.Transparent else gray04,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { onTimeSelected(item) }
                        .padding(vertical = 12.dp)
                ) {
                    Text(
                        text = formattedTime,
                        color = if (isSelected) Color.White else Color.Black,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TimeSectionPreview() {
    TimeSection(
        title = "오전",
        slots = listOf(
            AvailableTime("08:00"),
            AvailableTime("09:00"),
            AvailableTime("10:00"),
            AvailableTime("11:00"),
            AvailableTime("12:00"),
        ),
        selectedTime = null,
        onTimeSelected = {}
    )
}