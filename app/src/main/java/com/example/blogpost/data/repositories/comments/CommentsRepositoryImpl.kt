package com.example.blogpost.data.repositories.comments

import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.domain.comments.CommentsRepository
import com.example.blogpost.domain.comments.models.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class CommentsRepositoryImpl(
    private val api: BlogPostAPI,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : CommentsRepository {
    override fun getComments(): Flow<List<Comment>> = flow {
        val comments = api.getAllComments().records.map {
            it.toDomain()
        }
        emit(comments)
    }.flowOn(coroutineContext)

    override fun getCommentById(id: String): Flow<Comment> = flow {
        val comment = api.getCommentById(id).toDomain()
        emit(comment)
    }.flowOn(coroutineContext)
}