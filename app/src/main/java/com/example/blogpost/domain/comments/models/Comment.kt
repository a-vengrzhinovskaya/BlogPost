package com.example.blogpost.domain.comments.models

data class Comment(
    val id: Int,
    val postId: Int,
    val authorId: Int,
    val body: String
)