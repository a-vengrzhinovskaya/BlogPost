package com.example.blogpost.data.repositories.comments

import com.example.blogpost.data.network.models.comments.CommentsResponse
import com.example.blogpost.domain.comments.models.Comment

fun CommentsResponse.Record.toDomain() = Comment(
    id = id,
    postId = comment.post.first(),
    authorId = comment.author.first(),
    date = comment.date,
    body = comment.body
)