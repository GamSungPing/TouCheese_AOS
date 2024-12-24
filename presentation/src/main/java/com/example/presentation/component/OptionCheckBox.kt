package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.ProductOption
import com.example.presentation.R
import com.example.presentation.theme.gray10
import com.example.presentation.util.ext.toKoreanUnit


@Composable
fun OptionCheckBox(
    options: ProductOption,
    isChecked: Boolean,
    onClickOption: (Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(isChecked) }

    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 8.dp)
    ) {
        Icon(
            imageVector = if (checked) {
                ImageVector.vectorResource(id = R.drawable.ic_option_checked)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_option_unchecked)
            },
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickable {
                checked = !checked
                onClickOption(checked)
            }
        )

        Text(
            text = options.name,
            color = gray10,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )

        Text(
            text = options.price.toKoreanUnit(),
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            fontSize = 16.sp,
            color = gray10,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OptionCheckBoxPreview() {
    OptionCheckBox(
        options = ProductOption(
            name = "테스트",
            price = 1000
        ),
        isChecked = false,
        onClickOption = { }
    )
}