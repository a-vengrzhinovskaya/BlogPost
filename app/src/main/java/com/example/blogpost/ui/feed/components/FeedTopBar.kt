package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blogpost.R
import com.example.blogpost.ui.theme.extraLargeDp

@Composable
fun FeedTopBar(
    query: String,
    onQueryValueChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(extraLargeDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            labelText = "Поиск",
            onValueChange = onQueryValueChange,
            leadingIconId = R.drawable.ic_search
        )
    }
}