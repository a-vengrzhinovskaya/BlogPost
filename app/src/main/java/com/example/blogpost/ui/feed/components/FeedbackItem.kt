package com.example.blogpost.ui.feed.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.blogpost.ui.common.components.SmallSpacer

@Composable
fun FeedbackItem(
    iconId: Int,
    count: Int
) {
    Icon(
        painter = painterResource(iconId),
        tint = Color.Gray,
        contentDescription = null
    )

    SmallSpacer()

    Text(text = count.toString())
}