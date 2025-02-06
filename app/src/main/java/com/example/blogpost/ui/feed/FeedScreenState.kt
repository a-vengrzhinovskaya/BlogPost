package com.example.blogpost.ui.feed

import com.example.blogpost.ui.common.models.post.PostUI
import com.example.blogpost.ui.common.models.user.UserUI
import com.example.blogpost.ui.feed.models.PostWithAuthorUI

data class FeedScreenState(
    val postsWithAuthor: List<PostWithAuthorUI> = emptyList()
)
