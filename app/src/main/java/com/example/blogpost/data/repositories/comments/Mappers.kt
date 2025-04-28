package com.example.blogpost.data.repositories.comments

import com.example.blogpost.data.network.models.comments.CommentsResponse
import com.example.blogpost.domain.comments.models.Comment

fun CommentsResponse.CommentRecord.toDomain() = Comment(
    id = id,
    postId = post,
    authorId = authorId,
    date = date,
    body = body
)