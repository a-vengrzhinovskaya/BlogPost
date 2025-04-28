package com.example.blogpost.data.repositories.posts

import com.example.blogpost.data.network.models.posts.PostsResponse
import com.example.blogpost.domain.posts.models.Post

fun PostsResponse.PostRecord.toDomain() = Post(
    id = id,
    date = date,
    title = title,
    imageUrl = imageUrl,
    body = body,
    authorId = authorId,
    usersLikedIds = usersLikedIds ?: emptyList(),
    commentsIds = commentsIds ?: emptyList()
)