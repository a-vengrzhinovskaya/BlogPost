package com.example.blogpost.ui.common.models.post

data class PostUI(
    val title: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val body: String = "",
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val isLiked: Boolean = false
)
