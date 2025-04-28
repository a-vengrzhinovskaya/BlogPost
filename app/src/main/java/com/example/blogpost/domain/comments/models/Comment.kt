package com.example.blogpost.domain.comments.models

data class Comment(
    val id: String,
    val postId: String,
    val authorId: String,
    val date: String,
    val body: String
)