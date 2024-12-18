package com.example.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.gray10

@Composable
fun ReserveConfirmText(
    text: String,
    size: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = gray10,
        fontSize = size.sp,
        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun ReserveConfirmTextPreview() {
    ReserveConfirmText(
        text = "총 금액",
        size = 20
    )
}