package com.example.blogpost.ui.common.models.posts

data class PostUI(
    val id: String = "",
    val title: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val body: String = "",
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val isLiked: Boolean = false
)
