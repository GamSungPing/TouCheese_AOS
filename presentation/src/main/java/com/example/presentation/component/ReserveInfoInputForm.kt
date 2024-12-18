package com.example.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.studio.vm.model.ReservationPersonalInfo
import com.example.presentation.theme.gray04
import com.example.presentation.theme.gray07
import com.example.presentation.util.ext.emailFormatValidation
import com.example.presentation.util.ext.nameFormatValidation
import com.example.presentation.util.ext.phoneNumberFormatValidation

@Composable
fun ReserveInfoInputForm(
    title: String,
    placeholder: String,
    type: ReservationPersonalInfo,
    onValueChange: (String, Boolean) -> Unit,
) {
    var input by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 16.dp, top = 15.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("$title ")
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append("*")
                }
            },
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_medium))
        )

        Spacer(modifier = Modifier.width(20.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { newText ->
                input = newText
                val validation = validate(newText, type)
                onValueChange(newText, !validation)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
                .height(56.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium))
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = gray07,
                unfocusedIndicatorColor = gray04
            ),
            isError = input.isNotEmpty() && validate(input, type)
        )
    }
}

fun validate(input: String, type: ReservationPersonalInfo): Boolean {
    return when (type) {
        ReservationPersonalInfo.EMAIL -> !input.emailFormatValidation()
        ReservationPersonalInfo.NAME -> !input.nameFormatValidation()
        ReservationPersonalInfo.PHONE_NUMBER -> !input.phoneNumberFormatValidation()
    }
}

@Composable
@Preview(showBackground = true)
fun ReserveInfoInputFormPreview() {
    ReserveInfoInputForm(
        title = "이메일",
        placeholder = "이메일을 입력해주세요",
        type = ReservationPersonalInfo.EMAIL,
        onValueChange = { _, _ -> }
    )
}