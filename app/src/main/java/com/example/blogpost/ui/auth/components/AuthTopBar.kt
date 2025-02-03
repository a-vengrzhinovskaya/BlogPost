package com.example.blogpost.ui.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blogpost.ui.common.components.PrimaryButton

@Composable
fun AuthTopBar(
    topBarText: String,
    topBarButtonText: String,
    onTopBarButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = topBarText)
        PrimaryButton(text = topBarButtonText, onClick = onTopBarButtonClick)
    }
}