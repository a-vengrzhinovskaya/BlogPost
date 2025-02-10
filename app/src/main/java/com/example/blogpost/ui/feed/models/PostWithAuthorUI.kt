package com.example.blogpost.ui.feed.models

import com.example.blogpost.ui.common.models.posts.PostUI
import com.example.blogpost.ui.common.models.users.UserUI

data class PostWithAuthorUI(
    val post: PostUI,
    val author: UserUI
)
