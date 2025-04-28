package com.example.blogpost.ui.feed.models

import com.example.blogpost.domain.posts.models.PostWithAuthor
import com.example.blogpost.ui.common.models.posts.toUI
import com.example.blogpost.ui.common.models.users.toUI

fun PostWithAuthor.toUI() = PostWithAuthorUI(
    post = post.toUI(),
    author = author.toUI()
)