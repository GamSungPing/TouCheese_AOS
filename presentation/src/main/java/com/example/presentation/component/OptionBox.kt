package com.example.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.FakeProductOption
import com.example.domain.model.ProductOption

@Composable
fun OptionBox(
    options: List<ProductOption>?,
    onChangeCheckBoxState: (Boolean, ProductOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        options?.forEach { value ->
            OptionCheckBox(value) { checked, price ->
                onChangeCheckBoxState(checked, value)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OptionBoxPreview() {
    OptionBox(
        options = FakeProductOption,
        onChangeCheckBoxState = { _, _ -> }
    )
}