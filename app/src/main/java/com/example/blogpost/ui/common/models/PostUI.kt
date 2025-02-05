package com.example.blogpost.ui.common.models

data class PostUI(
    val title: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val author: AuthorUI = AuthorUI(),
    val body: String = "",
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val isLiked: Boolean = false
)
