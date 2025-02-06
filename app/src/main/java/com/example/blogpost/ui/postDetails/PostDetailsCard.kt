package com.example.blogpost.ui.postDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.common.models.post.PostUI
import com.example.blogpost.ui.feed.components.FeedbackItem
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun PostDetailsCard(
    post: PostUI,
    onLikeClick: () -> Unit
) {
    PrimaryCard {
        Text(text = post.title)

        MediumSpacer()

        Text(text = post.body)

        MediumSpacer()

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
            Row {
                FeedbackItem(
                    iconId = R.drawable.ic_like_filled,
                    count = post.likesCount
                )

                MediumSpacer()

                FeedbackItem(
                    iconId = R.drawable.ic_comments_filled,
                    count = post.commentsCount
                )
            }
        }
    }
}