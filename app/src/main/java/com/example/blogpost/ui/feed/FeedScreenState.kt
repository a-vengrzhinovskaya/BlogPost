package com.example.blogpost.ui.feed

import com.example.blogpost.ui.feed.models.PostWithAuthorUI

data class FeedScreenState(
    val postsWithAuthor: List<PostWithAuthorUI> = emptyList(),
    val query: String = ""
)
