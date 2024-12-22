package com.example.presentation.screen.reservation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.gray01
import com.example.presentation.theme.gray06
import com.example.presentation.theme.gray10
import com.example.presentation.theme.primary06
import com.example.presentation.theme.primary07

@Composable
fun ReservationCompleteScreen(
    onCheckScheduleClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_reserve_complete),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "예약 신청이 완료되었습니다!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    append("스튜디오와 최종 확인 후 예약이 \n")
                    withStyle(
                        style = SpanStyle(
                            color = primary06,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("확정되거나 취소되면")
                    }
                    append(" 알림을 받을 수 있습니다.")
                },
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = gray06
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = onCheckScheduleClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary06,
                    contentColor = gray10
                ),
                modifier = Modifier
                    .background(
                        color = primary06,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(282.dp)
                    .height(48.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "예약 일정 확인하러 가기",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = "Arrow",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onConfirmClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = gray01,
                    contentColor = primary07
                ),
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, primary07),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(282.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "확인",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    color = primary07
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReservationCompleteScreenPreview() {
    ReservationCompleteScreen(
        onCheckScheduleClick = {},
        onConfirmClick = {}
    )
}