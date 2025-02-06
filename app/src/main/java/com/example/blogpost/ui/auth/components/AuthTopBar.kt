package com.example.blogpost.ui.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthTopBar(
    topBarText: String,
    topBarButtonText: String,
    onTopBarButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = topBarText)
        Text(
            modifier = Modifier.clickable {
                onTopBarButtonClick()
            },
            text = topBarButtonText,
            color = MaterialTheme.colorScheme.primary
        )
    }
}