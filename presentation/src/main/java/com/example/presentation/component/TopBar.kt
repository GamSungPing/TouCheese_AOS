package com.example.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.presentation.R

@Composable
fun TopBar(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(top = 15.dp, start = 15.dp, bottom = 5.dp),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = stringResource(R.string.text_back),
            modifier = Modifier.clickable { onClick() }
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            modifier = Modifier
                .padding(end = 15.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview(){
    TopBar(
        title = "예약",
        onClick = {}
    )
}