package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blogpost.R

private const val BOTTOM_BAR_ICON_SIZE = 32

@Composable
fun FeedBottomBar(
    onFeedButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onProfileButtonClick: () -> Unit
) {
    NavigationBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                modifier = Modifier.size(BOTTOM_BAR_ICON_SIZE.dp),
                onClick = onFeedButtonClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    tint = Color.Gray,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .size(BOTTOM_BAR_ICON_SIZE.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
                onClick = onAddButtonClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier.size(BOTTOM_BAR_ICON_SIZE.dp),
                onClick = onProfileButtonClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    tint = Color.Gray,
                    contentDescription = null
                )
            }
        }
    }
}