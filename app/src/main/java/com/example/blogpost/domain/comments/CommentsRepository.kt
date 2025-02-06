package com.example.blogpost.domain.comments

import com.example.blogpost.domain.comments.models.Comment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    fun getComments(): Flow<List<Comment>>

    fun getCommentById(id: String): Flow<Comment>
}