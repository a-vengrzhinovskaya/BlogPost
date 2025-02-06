package com.example.blogpost.ui.feed.models

import com.example.blogpost.ui.common.models.post.PostUI
import com.example.blogpost.ui.common.models.user.UserUI

data class PostWithAuthorUI(
    val post: PostUI,
    val author: UserUI
)
