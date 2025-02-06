package com.example.blogpost.ui.postDetails

import com.example.blogpost.ui.common.models.post.PostUI

data class PostDetailsScreenState(
    val post: PostUI = PostUI(),
    val isLiked: Boolean = false
)