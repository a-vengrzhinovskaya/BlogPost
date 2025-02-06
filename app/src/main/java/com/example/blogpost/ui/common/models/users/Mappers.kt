package com.example.blogpost.ui.common.models.users

import com.example.blogpost.domain.users.models.User

fun User.toUI() = UserUI(
    name = name,
    avatarImageUrl = avatarUrl
)