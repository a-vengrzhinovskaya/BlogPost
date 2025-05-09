package com.example.blogpost.ui.postDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.ExtraLargeSpacer
import com.example.blogpost.ui.common.components.FeedbackItem
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.common.models.posts.PostUI
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun PostDetailsCard(
    post: PostUI,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit
) {
    var likeButtonTint by remember { mutableStateOf(Color.LightGray) }

    PrimaryCard {
        Text(
            text = post.title,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        LargeSpacer()
        Text(
            text = post.body,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
        ExtraLargeSpacer()
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(mediumDp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(post.imageUrl)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        MediumSpacer()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FeedbackItem(
                modifier = Modifier.size(24.dp),
                tint = likeButtonTint,
                iconId = R.drawable.ic_like_filled,
                count = post.likesCount,
                onClick = {
                    onLikeClick()
                    likeButtonTint = if (isLiked) Color.Gray else Color.LightGray
                }
            )
            SmallSpacer()
            FeedbackItem(
                modifier = Modifier.size(24.dp),
                iconId = R.drawable.ic_comments_filled,
                count = post.commentsCount,
                onClick = onCommentClick
            )
        }
    }
}