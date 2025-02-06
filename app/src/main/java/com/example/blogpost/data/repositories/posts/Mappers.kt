package com.example.blogpost.data.repositories.posts

import com.example.blogpost.data.network.models.posts.PostsResponse
import com.example.blogpost.domain.posts.models.Post

fun PostsResponse.Record.toDomain() = Post(
    id = id,
    date = post.date,
    title = post.title,
    imageUrl = post.imageUrl,
    body = post.body,
    authorId = post.authorId.first(),
    usersLikedIds = post.usersLikedIds ?: emptyList(),
    commentsIds = post.commentsIds ?: emptyList()
)