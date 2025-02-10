package com.example.blogpost.ui.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun FeedbackItem(
    modifier: Modifier = Modifier,
    tint: Color = Color.Gray,
    iconId: Int,
    count: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = modifier,
            painter = painterResource(iconId),
            tint = tint,
            contentDescription = null
        )
        SmallSpacer()
        Text(text = count.toString())
    }
}

@Composable
fun FeedbackItem(
    modifier: Modifier = Modifier,
    tint: Color = Color.Gray,
    iconId: Int,
    count: Int,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(
                modifier = modifier,
                painter = painterResource(iconId),
                tint = tint,
                contentDescription = null
            )
        }
        SmallSpacer()
        Text(text = count.toString())
    }
}