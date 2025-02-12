package com.example.blogpost.data.repositories.users

import com.example.blogpost.data.network.models.users.UsersResponse
import com.example.blogpost.domain.users.models.User

fun UsersResponse.Record.toDomain() = User(
    id = id,
    email = user.email,
    password = user.password,
    name = user.name,
    avatarUrl = user.avatarUrl,
    postsIds = user.posts ?: emptyList(),
    postsLikedIds = user.postsLiked ?: emptyList()
)