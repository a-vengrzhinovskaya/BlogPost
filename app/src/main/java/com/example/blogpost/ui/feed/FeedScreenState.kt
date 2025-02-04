package com.example.blogpost.ui.feed

import com.example.blogpost.ui.common.models.PostUI

data class FeedScreenState(
    val posts: List<PostUI> = emptyList()
)
