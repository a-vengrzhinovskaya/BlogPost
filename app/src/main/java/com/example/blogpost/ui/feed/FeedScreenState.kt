package com.example.blogpost.ui.feed

import com.example.blogpost.ui.feed.models.PostWithAuthorUI

data class FeedScreenState(
    val isAuthorized: Boolean = false,
    val postsWithAuthor: List<PostWithAuthorUI> = emptyList(),
    val currentUserPosts: List<PostWithAuthorUI> = emptyList(),
    val query: String = ""
)