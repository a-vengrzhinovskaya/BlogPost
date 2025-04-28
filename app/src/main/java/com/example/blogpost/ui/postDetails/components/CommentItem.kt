package com.example.blogpost.ui.postDetails.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.common.models.users.UserUI
import com.example.blogpost.ui.postDetails.models.CommentUI

@Composable
fun CommentItem(
    comment: CommentUI,
    author: UserUI
) {
    PrimaryCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(author.avatarImageUrl)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            SmallSpacer()
            Text(text = author.name, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = comment.date,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
        MediumSpacer()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = comment.body,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )
    }
}