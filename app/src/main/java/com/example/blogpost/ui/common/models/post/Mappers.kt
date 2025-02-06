package com.example.blogpost.ui.common.models.post

import com.example.blogpost.domain.posts.models.Post
import com.example.blogpost.ui.common.models.user.UserUI

fun Post.toUI() = PostUI(
    title = title,
    date = date,
    imageUrl = imageUrl,
    body = body,
    likesCount = usersLikedIds.size,
    commentsCount = commentsIds.size
)