package com.example.presentation.studio.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.calendar.DateSelectButton
import com.example.presentation.component.ReservationCard
import com.example.presentation.component.ReservationPeopleInfoCard
import com.example.presentation.component.ReservationProductCard
import com.example.presentation.component.ReserveConfirmText
import com.example.presentation.component.TopBar
import com.example.presentation.studio.navigation.parcelable.ReservationParcelable
import com.example.presentation.studio.vm.ReservationViewModel

@Composable
fun ReserveScreen(
    bundle: ReservationParcelable,
    navigateToBackStack: () -> Unit,
    navigateToReserveComplete: () -> Unit,
    viewModel: ReservationViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(bundle.productName) {
        viewModel.setSchedule(bundle)
    }

    ReserveScreen(
        context = context,
        studioName = bundle.studio.studioName,
        address = bundle.studio.address,
        reservationPersonName = "예약자명",
        reservationDate = bundle.date,
        reservationTime = bundle.time,
        imagePath = bundle.productImgPath,
        productName = bundle.productName,
        productPrice = bundle.payment,
        navigateToBackStack = navigateToBackStack,
        navigateToReserveComplete = {
            viewModel.requestReservation()
            navigateToReserveComplete()
        },
        onEmailChanged = { viewModel.onChangeEmail(it) },
        onNameChanged = { viewModel.onChangeName(it) },
        onPhoneNumberChanged = { viewModel.onChangePhoneNumber(it) },
    )
}

@Composable
fun ReserveScreen(
    context: Context,
    studioName: String,
    address: String,
    reservationPersonName: String,
    reservationDate: String,
    reservationTime: String,
    imagePath: String,
    productName: String,
    productPrice: String,
    navigateToBackStack: () -> Unit,
    navigateToReserveComplete: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onNameChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
) {
    var emailValidate by remember { mutableStateOf(false) }
    var nameValidate by remember { mutableStateOf(false) }
    var phoneNumberValidate by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.background(color = Color.White)
    ) {
        item {
            TopBar(
                title = stringResource(R.string.reservation),
                onClick = { navigateToBackStack() }
            )
        }
        item {
            ReservationCard(
                studioName = studioName,
                address = address,
                reservationPersonName = reservationPersonName,
                reservationDate = reservationDate,
                reservationTime = reservationTime
            )
        }
        item {
            ReservationProductCard(
                context = context,
                imagePath = imagePath,
                productName = productName,
                productPrice = productPrice
            )
        }
        item {
            ReservationPeopleInfoCard(
                onEmailChanged = { value, isValid ->
                    emailValidate = isValid
                    onEmailChanged(value)
                },
                onNameChanged = { value, isValid ->
                    nameValidate = isValid
                    onNameChanged(value)
                },
                onPhoneNumberChanged = { value, isValid ->
                    phoneNumberValidate = isValid
                    onPhoneNumberChanged(value)
                },
            )
        }
        item {
            ReserveConfirmText(
                text = stringResource(R.string.payment_info),
                size = 20,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 15.dp)
            )
        }
//        item {
//            Row(
//                modifier = Modifier
//                    .wrapContentSize()
//                    .padding(10.dp)
//            ) {
//                ReserveConfirmText(
//                    text = stringResource(R.string.total_price),
//                    size = 18,
//                    modifier = Modifier.padding(start = 15.dp)
//                )
//                Spacer(modifier = Modifier.weight(1.0f))
//                ReserveConfirmText(
//                    text = productPrice,
//                    size = 18,
//                    modifier = Modifier.padding(end = 10.dp)
//                )
//            }
//        }

        item {
            DateSelectButton(
                title = stringResource(R.string.text_submit_reserve),
                clickable = emailValidate && nameValidate && phoneNumberValidate,
                onClick = { navigateToReserveComplete() },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReserveScreenPreview() {
    ReserveScreen(
        context = LocalContext.current,
        studioName = "스튜디오 이름",
        address = "경기도 수원시 팔달구 매향동",
        reservationPersonName = "김인직",
        reservationDate = "2024 12월 24일",
        reservationTime = "13:00",
        imagePath = "",
        productName = "상품명",
        productPrice = "250,000원",
        onEmailChanged = { },
        onNameChanged = { },
        onPhoneNumberChanged = { },
        navigateToBackStack = {},
        navigateToReserveComplete = {}
    )
}