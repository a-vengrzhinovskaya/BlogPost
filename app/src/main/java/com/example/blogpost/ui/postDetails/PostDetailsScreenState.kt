package com.example.blogpost.ui.postDetails

import com.example.blogpost.ui.common.models.PostUI

data class PostDetailsScreenState(
    val post: PostUI = PostUI(),
    val isLiked: Boolean
)