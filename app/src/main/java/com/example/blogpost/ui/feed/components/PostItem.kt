package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.ExtraLargeSpacer
import com.example.blogpost.ui.common.components.FeedbackItem
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.common.models.posts.PostUI
import com.example.blogpost.ui.common.models.users.UserUI
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun PostItem(
    post: PostUI, author: UserUI, onPostClick: (String) -> Unit
) {
    PrimaryCard(modifier = Modifier.clickable { onPostClick(post.id) }) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current).data(author.avatarImageUrl)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            SmallSpacer()
            Text(text = author.name, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = post.date, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        }
        LargeSpacer()
        Text(
            text = post.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        SmallSpacer()
        MediumSpacer()
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(mediumDp)),
            model = ImageRequest.Builder(LocalContext.current).data(post.imageUrl).build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        ExtraLargeSpacer()
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                FeedbackItem(
                    modifier = Modifier.size(24.dp),
                    iconId = R.drawable.ic_like_filled,
                    count = post.likesCount
                )

                MediumSpacer()
                FeedbackItem(
                    modifier = Modifier.size(24.dp),
                    iconId = R.drawable.ic_comments_filled,
                    count = post.commentsCount
                )
            }
        }
    }
}