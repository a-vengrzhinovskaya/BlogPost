package com.example.blogpost.ui.common.models

data class PostUI(
    val name: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val author: AuthorUI,
    val text: String,
    val likesCount: Int,
    val commentsCount: Int,
    val isLiked: Boolean = false
)
