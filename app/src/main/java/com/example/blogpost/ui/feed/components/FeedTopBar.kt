package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.theme.extraLargeDp

@Composable
fun FeedTopBar(
    query: String,
    onQueryValueChange: (String) -> Unit,
    onAddButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(extraLargeDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchTextField(
            modifier = Modifier.weight(1f),
            value = query,
            labelText = "Поиск",
            onValueChange = onQueryValueChange,
            leadingIconId = R.drawable.ic_search
        )
        LargeSpacer()
        IconButton(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            onClick = onAddButtonClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null
            )
        }
    }
}