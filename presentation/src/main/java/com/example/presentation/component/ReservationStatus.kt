package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.errorColor
import com.example.presentation.theme.gray01
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray06
import com.example.presentation.theme.gray09
import com.example.presentation.theme.primary05
import com.example.presentation.theme.primary07

@Composable
fun ReservationStatus() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .border(width = 1.dp, color = gray03)
            .background(color = gray02, shape = RoundedCornerShape(20.dp))
    ) {

        BasicText(
            text = "※ 예약 상태는 아래와 같습니다.",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color(0xFF2B89FE),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // 각 상태
        ReservationStatusRow(
            title = "예약 대기",
            description = "스튜디오에 예약을 신청한 상태입니다. 스튜디오에서 확인이 되면 예약 완료나 예약 취소 상태로 변경됩니다.",
            titleBackgroundColor = gray03,
            borderColor = gray03,
            titleTextColor = gray09
        )
        ReservationStatusRow(
            title = "예약 확정",
            description = "스튜디오에서 예약을 완료한 상태입니다. 예약 날짜에 촬영을 진행하면 됩니다. 사용자가 예약을 취소할 수 있는 상태입니다.",
            titleBackgroundColor = gray01,
            borderColor = primary07,
            titleTextColor = primary07
        )
        ReservationStatusRow(
            title = "예약 취소",
            description = "스튜디오에서 예약을 취소한 상태입니다. 다른 날짜로 예약을 다시 진행해야 합니다.",
            titleBackgroundColor = gray01,
            titleTextColor = errorColor,
            borderColor = errorColor
        )
        ReservationStatusRow(
            title = "촬영 완료",
            description = "사용자가 예약한 시간에 촬영이 완료된 상태입니다. 스튜디오에 리뷰를 남길 수 있습니다.",
            titleBackgroundColor = primary05,
            titleTextColor = gray09,
            borderColor = primary05
        )
    }
}

@Composable
fun ReservationStatusRow(
    title: String,
    description: String,
    titleBackgroundColor: Color,
    borderColor: Color,
    titleTextColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, borderColor, RoundedCornerShape(4.dp))
                .background(titleBackgroundColor, RoundedCornerShape(4.dp), )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            BasicText(
                text = title,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleTextColor,
                ),

            )
        }

        BasicText(
            text = description,
            style = TextStyle(
                fontSize = 12.sp,
                color = gray06,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReservationStatusScreenPreview() {
    ReservationStatus()
}
