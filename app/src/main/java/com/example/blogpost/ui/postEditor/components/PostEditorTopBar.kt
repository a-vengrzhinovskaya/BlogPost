package com.example.blogpost.ui.postEditor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blogpost.R
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun PostEditorTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit
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
        Button(
            shape = RoundedCornerShape(mediumDp),
            onClick = onSaveClick
        ) {
            Text(text = "Опубликовать")
        }
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onDeleteClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}