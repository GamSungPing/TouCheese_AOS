package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.gray01
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray06
import com.example.presentation.theme.gray10

@Composable
fun ReservationCard(
    studioName: String,
    address: String,
    reservationPersonName: String,
    reservationDate: String,
    reservationTime: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = gray01)
    ) {
        Text(
            text = stringResource(R.string.reserve_info),
            color = gray10,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            modifier = Modifier.padding(10.dp)
        )
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = gray03,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = studioName,
                    color = gray10,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    modifier = Modifier.padding(10.dp)
                )

                Row(
                    modifier = Modifier
                        .background(color = gray02)
                        .border(1.dp, color = gray01, shape = RoundedCornerShape(10.dp))
                        .padding(15.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_location),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = address,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = gray06
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .background(color = gray02)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                ReserveItem("예약자 성함", reservationPersonName)
                ReserveItem("예약 날짜", reservationDate)
                ReserveItem("예약 시간", reservationTime)
            }
        }
    }
}

@Composable
@Preview
fun ReservationCardPreview() {
    ReservationCard(
        studioName = "스튜디오 이름",
        address = "주소",
        reservationPersonName = "예약자 성함",
        reservationDate = "예약 날짜",
        reservationTime = "예약 시간"
    )
}