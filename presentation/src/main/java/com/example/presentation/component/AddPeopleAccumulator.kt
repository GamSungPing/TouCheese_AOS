package com.example.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun AddPeopleAccumulator(
    onClickAdd: () -> Unit,
    onClickSub: () -> Unit
) {
    var count by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = {
                if (count > 0) {
                    count--
                    onClickSub()
                }
        },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_btn_sub),
                tint = Color.Unspecified,
                contentDescription = stringResource(R.string.btn_sub)
            )
        }

        Text(
            text = "${count}명",
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )

        IconButton(
            onClick = {
                count++
                onClickAdd()
            },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bnt_add),
                tint = Color.Unspecified,
                contentDescription = stringResource(R.string.btn_add)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddPeopleAccumulatorPreview() {
    AddPeopleAccumulator(
        onClickAdd = {},
        onClickSub = {}
    )
}