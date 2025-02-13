package com.example.blogpost.domain.comments

import com.example.blogpost.domain.comments.models.Comment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    fun getCommentById(id: String): Flow<Comment>

    fun createComment(
        postId: String,
        authorId: String,
        date: String,
        body: String
    ): Flow<Comment>
}