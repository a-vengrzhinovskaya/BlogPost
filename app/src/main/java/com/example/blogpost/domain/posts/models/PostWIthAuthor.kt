package com.example.blogpost.domain.posts.models

import com.example.blogpost.domain.users.models.User

data class PostWithAuthor (
    val post: Post,
    val author: User
)