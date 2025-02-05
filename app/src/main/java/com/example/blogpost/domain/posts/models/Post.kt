package com.example.blogpost.domain.posts.models

data class Post(
    val id: Int,
    val title: String,
    val date: String,
    val imageUrl: String,
    val body: String,
    val authorId: Int,
    val usersLikedIds: List<Int>,
    val commentsIds: List<Int>
)
