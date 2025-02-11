package com.example.blogpost.ui.postDetails

import com.example.blogpost.ui.common.models.posts.PostUI
import com.example.blogpost.ui.postDetails.models.CommentWithAuthorUI

data class PostDetailsScreenState(
    val post: PostUI = PostUI(),
    val isLiked: Boolean = false,
    val commentsWithAuthor: List<CommentWithAuthorUI> = emptyList(),
    val currentUserComment: String = ""
)