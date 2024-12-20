package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.gray02
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray08

@Composable
fun ReviewButton(
    reviewCount: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .background(
                color = gray02,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = gray03,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_review),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )

            Text(
                color = gray08,
                text = "${reviewCount}개"
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ReviewButtonPreview() {
    ReviewButton(
        reviewCount = 108,
        onClick = {}
    )
}