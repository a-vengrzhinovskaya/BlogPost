package com.example.blogpost.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blogpost.R
import com.example.blogpost.ui.theme.extraLargeDp

@Composable
fun ProfileTopBar(
    text: String,
    onBackClick: () -> Unit,
    isEditMode: Boolean,
    onEditButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(extraLargeDp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                tint = Color.Gray,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onEditButtonClick
        ) {
            Icon(
                painter = painterResource(if (isEditMode) R.drawable.ic_save else R.drawable.ic_edit),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}