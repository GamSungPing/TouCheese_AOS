package com.example.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.rule.Output.Companion.basePeopleAmountFormat
import com.example.presentation.R
import com.example.presentation.theme.gray10

@Composable
fun GroupOption(
    basePeopleAmount: Int,
    onClickAdd: () -> Unit,
    onClickSub: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "기준 인원",
            color = gray10,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )
        Text(
            text = basePeopleAmountFormat(basePeopleAmount),
            color = gray10,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "추가 인원",
            color = gray10,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )
        AddPeopleAccumulator(
            onClickAdd = onClickAdd,
            onClickSub = onClickSub
        )
    }
}