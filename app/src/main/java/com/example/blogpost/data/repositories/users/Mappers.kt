package com.example.blogpost.data.repositories.users

import com.example.blogpost.data.network.models.users.UsersResponse
import com.example.blogpost.domain.users.models.User

fun UsersResponse.UserRecord.toDomain() = User(
    id = id,
    email = email,
    password = password,
    name = name,
    avatarUrl = avatarUrl,
    postsIds = posts ?: emptyList(),
    postsLikedIds = postsLiked ?: emptyList()
)