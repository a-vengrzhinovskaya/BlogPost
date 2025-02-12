package com.example.blogpost.domain.users.models

data class User(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val avatarUrl: String,
    val postsIds: List<String>,
    val postsLikedIds: List<String>
)
