package com.example.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.gray05

@Composable
fun OptionLine(
    title: String,
    content: String
) {
    Row {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_option_icon),
            contentDescription = null,
            tint = gray05
        )
        OptionLineText(title)
        Spacer(modifier = Modifier.weight(1.0f))
        OptionLineText(content)
    }
}

@Composable
fun OptionLineText(text: String){
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 16.sp,
        color = gray05
    )
}

@Composable
@Preview(showBackground = true)
fun OptionLinePreview() {
    OptionLine(
        "보정 사진 추가",
        "+60,000원"
    )
}