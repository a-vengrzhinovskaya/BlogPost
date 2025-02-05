package com.example.blogpost.domain.posts.models

data class Post(
    val id: String,
    val title: String,
    val date: String,
    val imageUrl: String,
    val body: String,
    val authorId: String,
    val usersLikedIds: List<String>,
    val commentsIds: List<String>
)
