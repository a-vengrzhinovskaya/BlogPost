package com.example.blogpost.ui.postDetails.models

import com.example.blogpost.domain.comments.models.Comment
import com.example.blogpost.domain.comments.models.CommentWithAuthor
import com.example.blogpost.ui.common.models.users.toUI

fun Comment.toUI() = CommentUI(
    body = body,
    date = date
)

fun CommentWithAuthor.toUI() = CommentWithAuthorUI(
    comment = comment.toUI(),
    author = author.toUI()
)