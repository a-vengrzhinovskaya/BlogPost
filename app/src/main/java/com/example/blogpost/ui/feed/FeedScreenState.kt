package com.example.blogpost.ui.feed

import com.example.blogpost.ui.common.models.post.PostUI
import com.example.blogpost.ui.common.models.user.UserUI

data class FeedScreenState(
    val postsWithAuthor: List<Pair<PostUI, UserUI>> = emptyList()
)
