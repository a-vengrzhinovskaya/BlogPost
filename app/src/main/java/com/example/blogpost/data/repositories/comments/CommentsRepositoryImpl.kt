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
    private val commentsCache = mutableListOf<Comment>()

    override fun getComments(): Flow<List<Comment>> = flow {
        val comments = api.getAllComments().records.map {
            it.toDomain()
        }
        cacheComments(comments)
        emit(comments)
    }.flowOn(coroutineContext)

    override fun getCommentById(id: String): Flow<Comment> = flow {
        val comment = commentsCache.firstOrNull { it.id == id } ?: api.getCommentById(id).toDomain()
        emit(comment)
    }.flowOn(coroutineContext)

    private fun cacheComments(newComments: List<Comment>) = newComments.forEach { newComment ->
        if (!commentsCache.contains(newComment)) {
            commentsCache.add(newComment)
        }
    }
}