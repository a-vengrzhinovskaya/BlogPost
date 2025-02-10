package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blogpost.ui.feed.models.PostWithAuthorUI
import com.example.blogpost.ui.theme.extraLargeDp

@Composable
fun PostsList(
    posts: List<PostWithAuthorUI>,
    onPostClick: (String) -> Unit
) {
    if (posts.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ничего не нашлось"
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(extraLargeDp)
        ) {
            items(posts) {
                PostItem(
                    post = it.post,
                    author = it.author,
                    onPostClick = onPostClick
                )
            }
        }
    }
}