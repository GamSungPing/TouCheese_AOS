package com.example.presentation.studio.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.AvailableTime
import com.example.domain.model.FakeProductOption
import com.example.domain.model.ProductOption
import com.example.domain.rule.Grouping
import com.example.presentation.component.GroupingBox
import com.example.presentation.component.OptionBox
import com.example.presentation.component.ProductDetailHeader
import com.example.presentation.component.ProductReserveButton
import com.example.presentation.component.ReservationLayer
import com.example.presentation.component.TopBar
import com.example.presentation.studio.navigation.parcelable.ProductInfoParcelable
import com.example.presentation.studio.navigation.parcelable.ReservationParcelable
import com.example.presentation.studio.vm.ProductDetailViewModel
import com.example.presentation.theme.gray01
import com.example.presentation.theme.gray03
import com.example.presentation.util.ext.toKoreanUnit
import java.time.LocalDate

@Composable
fun ProductDetailScreen(
    bundle: ProductInfoParcelable,
    navigateToBackStack: () -> Unit,
    navigateToReserve: (ReservationParcelable) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val productId = bundle.productId
    val description = bundle.description
    val path = bundle.profileUrl

    LaunchedEffect(productId) {
        if (productId != 0) viewModel.load(productId)
    }

    ProductDetailScreen(
        productId = productId,
        productName = state.product.productName,
        description = description,
        path = path,
        options = state.product.productOptions,
        basePeopleAmount = state.product.basePeopleCnt,
        productPrice = state.productPriceMessage,
        isGroup = state.product.isGroup,
        paymentMessage = state.paymentMessage,
        dateButtonTitle = state.scheduleMessage,
        submitAble = state.schedule == null,
        onClickBackButton = { navigateToBackStack() },
        onChangeCheckBoxState = { checked, price ->
            if (checked) viewModel.addOption(price)
            else viewModel.subOption(price)
        },
        onCompleteSelectTime = { date, time ->
            viewModel.setSchedule(date, time)
        },
        onClickSubmit = {
            navigateToReserve(
                ReservationParcelable(
                    studio = bundle,
                    selectedOption = state.selectedOption,
                    productName = state.product.productName,
                    productImgPath = path,
                    addPeopleCount = state.addGuestCount,
                    payment = state.payment.toKoreanUnit(),
                    date = state.schedule?.date.toString(),
                    time = state.schedule?.time?.value.toString()
                )
            )
        },
        onClickAddPeople = { viewModel.increaseAddGuestCount() },
        onClickSubPeople = { viewModel.decreaseAddGuestCount() }
    )
}

@Composable
fun ProductDetailScreen(
    productId: Int,
    productName: String,
    description: String,
    path: String,
    options: List<ProductOption>?,
    basePeopleAmount: Int,
    productPrice: String,
    isGroup: Grouping,
    paymentMessage: String,
    dateButtonTitle: String,
    submitAble: Boolean,
    onClickBackButton: () -> Unit,
    onChangeCheckBoxState: (Boolean, ProductOption) -> Unit,
    onCompleteSelectTime: (LocalDate, AvailableTime) -> Unit,
    onClickSubmit: () -> Unit,
    onClickAddPeople: () -> Unit,
    onClickSubPeople: () -> Unit,
) {
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(bottom = 70.dp),
        modifier = Modifier.background(color = gray01)
    ) {
        item { TopBar("주문/예약", onClickBackButton) }
        item {
            ProductDetailHeader(
                context = context,
                path = path,
                productName = productName,
                description = description
            )
        }
        item {
            OptionBox(
                options = options,
                onChangeCheckBoxState = onChangeCheckBoxState
            )
        }

        item {
            GroupingBox(
                isGroup = isGroup,
                basePeopleAmount = basePeopleAmount,
                productPrice = productPrice,
                onClickAdd = { onClickAddPeople() },
                onClickSub = { onClickSubPeople() }
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(gray03)
            )
        }

        item {
            ReservationLayer(
                productId = productId,
                dateButtonTitle = dateButtonTitle,
                onCompleteSelectTime = onCompleteSelectTime
            )
        }
        item {
            ProductReserveButton(
                paymentMessage = paymentMessage,
                submitAble = submitAble,
                onClickSubmit = onClickSubmit
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        productId = 1,
        productName = "테스트",
        description = "테스트",
        path = "",
        options = FakeProductOption,
        basePeopleAmount = 1,
        productPrice = "17,000원",
        Grouping.NOT_GROUP,
        paymentMessage = "선택 상품 주문(17,000원)",
        dateButtonTitle = "예약일자 및 시간 선택",
        onClickBackButton = {},
        onChangeCheckBoxState = { _, _ -> },
        onCompleteSelectTime = { _, _ -> },
        onClickSubmit = {},
        submitAble = false,
        onClickAddPeople = {},
        onClickSubPeople = {}
    )
}