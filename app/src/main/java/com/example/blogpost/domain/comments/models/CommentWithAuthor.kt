package com.example.blogpost.domain.comments.models

import com.example.blogpost.domain.users.models.User

data class CommentWithAuthor(
    val comment: Comment,
    val author: User
)
