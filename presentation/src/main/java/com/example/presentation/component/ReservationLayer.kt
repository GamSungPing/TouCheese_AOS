package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AvailableTime
import com.example.presentation.R
import com.example.presentation.calendar.CalendarBottomSheet
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray05
import java.time.LocalDate

@Composable
fun ReservationLayer(
    productId: Int,
    dateButtonTitle: String,
    onCompleteSelectTime: (LocalDate, AvailableTime) -> Unit
) {
    var showCalendar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Text(
            text = stringResource(R.string.reservation_date),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(gray02)
                .padding(vertical = 15.dp)
                .fillMaxWidth()
                .clickable { showCalendar = true }
        ) {
            Text(
                text = dateButtonTitle,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                fontSize = 18.sp,
                color = gray05
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                tint = gray05,
                modifier = Modifier
                    .size(20.dp)
            )
        }

        if (showCalendar) {
            CalendarBottomSheet(
                productId = productId,
                onDismiss = {
                    showCalendar = false
                },
                onClickSubmit = { date, time ->
                    onCompleteSelectTime(date, time)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReservationLayerPreview() {
    ReservationLayer(
        1,
        "예약 날짜를 선택해주세요",
        { _, _ -> }
    )
}