package com.example.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AvailableTime
import com.example.presentation.R

@Composable
fun TimeTable(
    morningSlots: List<AvailableTime>,
    afternoonSlots: List<AvailableTime>,
    selectedTime: String?,
    onTimeSelected: (AvailableTime) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.text_selectable_time_table),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )
        if (morningSlots.isNotEmpty()) {
            TimeSection(
                title = stringResource(R.string.morning),
                slots = morningSlots,
                selectedTime = selectedTime,
                onTimeSelected = onTimeSelected
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (afternoonSlots.isNotEmpty()) {
            TimeSection(
                title = stringResource(R.string.afternoon),
                slots = afternoonSlots,
                selectedTime = selectedTime,
                onTimeSelected = onTimeSelected
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun TimeTablePreview() {
    TimeTable(
        morningSlots = listOf(
            AvailableTime("08:00"),
            AvailableTime("09:00"),
            AvailableTime("10:00"),
            AvailableTime("11:00"),
        ),
        afternoonSlots = listOf(
            AvailableTime("12:00"),
            AvailableTime("13:00"),
            AvailableTime("14:00"),
            AvailableTime("15:00"),
        ),
        selectedTime = null,
        onTimeSelected = {}
    )
}