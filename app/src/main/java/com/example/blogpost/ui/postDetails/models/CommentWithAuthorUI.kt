package com.example.blogpost.ui.postDetails.models

import com.example.blogpost.ui.common.models.users.UserUI

data class CommentWithAuthorUI(
    val comment: CommentUI,
    val author: UserUI
)
