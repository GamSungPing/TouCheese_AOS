package com.example.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.AvailableTime
import com.example.presentation.R
import com.example.presentation.calendar.vm.CalendarViewModel
import com.example.presentation.calendar.vm.model.CalendarState
import com.example.presentation.theme.gray02
import com.example.presentation.util.toCalendarTitle
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalendarBottomSheet(
    productId: Int,
    onDismiss: () -> Unit,
    onClickSubmit: (LocalDate, AvailableTime) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModel.load(productId)
    }

    CalendarBottomSheet(
        state = state,
        onDismiss = onDismiss,
        onSelectedDate = { date ->
            viewModel.setSelectedDate(date)
            viewModel.loadAvailableReservationTime(productId, date)
        },
        onSelectedTime = { time ->
            viewModel.setSelectedTime(time)
        },
        onClickSubmit
    )
}

@Composable
fun CalendarBottomSheet(
    state: CalendarState,
    onDismiss: () -> Unit,
    onSelectedDate: (LocalDate) -> Unit,
    onSelectedTime: (AvailableTime) -> Unit,
    onClickSubmit: (LocalDate, AvailableTime) -> Unit,
) {
    val selectedDate = state.selectedDate
    val initialPage = state.initialPage
    val pageCount = state.pageCount

    val pagerState = rememberPagerState(pageCount = { pageCount }, initialPage = initialPage)
    var currentDate by remember { mutableStateOf(selectedDate) }
    var currentPage by remember { mutableIntStateOf(initialPage) }
    val animationScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        val swipe = (pagerState.currentPage - currentPage).toLong()
        currentDate = currentDate.plusMonths(swipe)
        currentPage = pagerState.currentPage
    }

    BottomSheetDialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 12.dp)
            ) {
                CalendarHeader(
                    title = toCalendarTitle(currentDate.year, currentDate.monthValue),
                    onBackClick = {
                        val previousPage = pagerState.currentPage - 1
                        if (previousPage >= 0) {
                            animationScope.launch {
                                pagerState.animateScrollToPage(previousPage)
                            }
                        }
                    },
                    onForwardClick = {
                        animationScope.launch {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                )

                CalendarInBottomSheet(
                    pagerState = pagerState,
                    selectedDate = state.selectedDate,
                    onSelectedDate = {
                        onSelectedDate(it)
                    }
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(2.dp)
                        .background(color = gray02)
                )

                TimeTable(
                    morningSlots = state.time.morningSlots,
                    afternoonSlots = state.time.afternoonSlots,
                    selectedTime = state.selectedTime?.value,
                    onTimeSelected = {
                        onSelectedTime(it)
                    },
                    modifier = Modifier
                        .heightIn(min = 100.dp, max = 300.dp)
                        .padding(top = 12.dp)
                )

                DateSelectButton(
                    title = stringResource(R.string.select_date),
                    clickable = state.selectedTime != null,
                    onClick = {
                        state.selectedTime?.let { selectedTime ->
                            onClickSubmit(
                                state.selectedDate,
                                selectedTime
                            )
                            onDismiss()
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun CalendarBottomSheetPreview() {
    CalendarBottomSheet(
        state = CalendarState.create(),
        onDismiss = {},
        onSelectedDate = {},
        onSelectedTime = {},
        onClickSubmit = { _, _ -> },
    )
}