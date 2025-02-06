package com.example.blogpost.ui.common.models.post

import com.example.blogpost.domain.posts.models.Post

fun Post.toUI() = PostUI(
    id = id,
    title = title,
    date = date,
    imageUrl = imageUrl,
    body = body,
    likesCount = usersLikedIds.size,
    commentsCount = commentsIds.size
)