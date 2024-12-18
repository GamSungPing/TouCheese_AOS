package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray05
import com.example.presentation.theme.gray10
import com.example.presentation.theme.primary06

@Composable
fun ProductReserveButton(
    paymentMessage: String,
    submitAble: Boolean,
    onClickSubmit: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 10.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (!submitAble) primary06 else gray03,
            ),
            onClick = { if (!submitAble) onClickSubmit() },
        ) {
            Text(
                text = paymentMessage,
                color = if (!submitAble) gray10 else gray05,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview
fun ProductReserveButtonPreview() {
    ProductReserveButton(
        paymentMessage = "선택 상품 주문(17,000원)",
        submitAble = false,
        onClickSubmit = {}
    )
}