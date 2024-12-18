package com.example.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.gray06
import com.example.presentation.theme.gray09

@Composable
fun ReserveItem(title: String, content: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = gray06
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = content,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = gray09
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ReserveItemPreview(){
    ReserveItem(title = "예약자", content = "김인직")
}