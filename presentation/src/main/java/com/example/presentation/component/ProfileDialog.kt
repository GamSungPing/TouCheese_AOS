package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun ProfileDialog(
    title: String,
    btnTitle: String = stringResource(R.string.text_modify),
    onClickPositive: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
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
                    text = title,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                DateSelectButton(
                    title = btnTitle,
                    clickable = true,
                    onClick = {
                        onClickPositive()
                    },
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDialogPreview() {
    ProfileDialog(
        title = "로그아웃 하시겠습니까?",
        onClickPositive = {},
        onDismiss = {}
    )
}