package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.presentation.R
import com.example.presentation.calendar.DateSelectButton
import com.example.presentation.theme.gray04
import com.example.presentation.theme.gray07
import com.example.presentation.theme.gray10
import com.example.presentation.theme.primary06

@Composable
fun ModifyNickNameModal(
    nickName: String,
    onComplete: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var input by remember { mutableStateOf(nickName) }
    var isClickable by remember { mutableStateOf(input.isNotBlank()) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_close_24px),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDismiss() }
                )

                Text(
                    text = stringResource(R.string.text_modify_nickname),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )

                Text(
                    text = stringResource(R.string.text_nickname),
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )

                OutlinedTextField(
                    value = input,
                    onValueChange = { newText ->
                        if (newText.length <= 10) {
                            input = newText
                        }
                        isClickable = input.isNotBlank()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),

                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = gray07,
                        unfocusedIndicatorColor = gray04
                    ),
                    isError = input.isBlank() || input.length > 10,
                    trailingIcon = {
                        if (input.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    input = ""
                                    isClickable = false
                                }
                            )
                        }
                    }
                )

                Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp)) {
                    Text(
                        text = input.length.toString(),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                    )

                    Text(
                        text = "/10 글자",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                    )
                }

                DateSelectButton(
                    title = stringResource(R.string.text_modify),
                    clickable = isClickable,
                    onClick = {
                        onComplete(input)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun DuplicateCheckButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = primary06),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.text_check_duplicate),
            color = gray10,
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            fontSize = 14.sp
        )
    }
}

@Composable
@Preview
fun ModifyNickNameModalPreview() {
    ModifyNickNameModal(
        nickName = "닉네임",
        onComplete = {},
        onDismiss = {}
    )
}