package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.presentation.R
import com.example.presentation.screen.studio.vm.model.ReservationPersonalInfo
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray10

@Composable
fun ReservationPeopleInfoCard(
    onEmailChanged: (String, Boolean) -> Unit,
    onNameChanged: (String, Boolean) -> Unit,
    onPhoneNumberChanged: (String, Boolean) -> Unit,
) {
    Column {
        Spacer(
            modifier = Modifier
                .background(color = gray02)
                .fillMaxWidth()
                .height(5.dp)
        )

        Text(
            text = stringResource(R.string.reserve_people_info),
            color = gray10,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    top = 35.dp,
                )
        )

        Column {
            ReserveInfoInputForm(
                title = "이메일",
                placeholder = "이메일을 입력해주세요",
                type = ReservationPersonalInfo.EMAIL,
                onValueChange = { value, isValid ->
                    onEmailChanged(value, isValid)
                }
            )
            ReserveInfoInputForm(
                title = "예약자",
                placeholder = "이름을 입력해주세요",
                type = ReservationPersonalInfo.NAME,
                onValueChange = { value, isValid ->
                    onNameChanged(value, isValid)
                }
            )
            ReserveInfoInputForm(
                title = "휴대폰",
                placeholder = "전화번호를 입력해주세요",
                type = ReservationPersonalInfo.PHONE_NUMBER,
                onValueChange = { value, isValid ->
                    onPhoneNumberChanged(value, isValid)
                }
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        Spacer(
            modifier = Modifier
                .background(color = gray02)
                .fillMaxWidth()
                .height(5.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ReservationPeopleInfoCardPreview() {
    ReservationPeopleInfoCard(
        onEmailChanged = { _, _ -> },
        onNameChanged = { _, _ -> },
        onPhoneNumberChanged = { _, _ -> },
    )
}