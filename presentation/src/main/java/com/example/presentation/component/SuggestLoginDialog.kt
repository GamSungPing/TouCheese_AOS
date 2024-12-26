package com.example.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.presentation.R
import com.example.presentation.calendar.DateSelectButton

@Composable
fun SuggestLoginDialog(
    onClickPositive: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(177.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.text_have_to_login),
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = stringResource(R.string.text_would_you_login),
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    DateSelectButton(
                        title = stringResource(R.string.text_cancel),
                        clickable = true,
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(0.3f)
                    )

                    DateSelectButton(
                        title = stringResource(R.string.text_go_to_login),
                        clickable = true,
                        onClick = {
                            onClickPositive()
                        },
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .weight(0.6f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestLoginDialogPreview() {
    SuggestLoginDialog(
        onClickPositive = {},
        onDismiss = {}
    )
}
