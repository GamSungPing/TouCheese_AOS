package com.example.presentation.calendar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.theme.gray10
import com.example.presentation.theme.primary06

@Composable
fun DateSelectButton(
    title: String,
    clickable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(containerColor = primary06),
) {
    Button(
        onClick = onClick,
        enabled = clickable,
        colors = buttonColors,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(48.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = gray10,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun DateSelectButtonPreview() {
    DateSelectButton(
        "날짜 선택",
        clickable = true,
        onClick = {}
    )
}