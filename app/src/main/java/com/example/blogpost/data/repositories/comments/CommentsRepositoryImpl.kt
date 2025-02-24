package com.example.blogpost.data.repositories.comments

import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.data.network.models.comments.CommentsResponse
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

    override fun getCommentById(id: String): Flow<Comment> = flow {
        val comment = commentsCache.firstOrNull { it.id == id } ?: api.getCommentById(id)
            .toDomain() // TODO: chache
        emit(comment)
    }.flowOn(coroutineContext)

    override suspend fun createComment(
        postId: String,
        authorId: String,
        date: String,
        body: String
    ) = api.createComment(
        CommentsResponse.CommentRecord(
            post = postId,
            authorId = authorId,
            date = date,
            body = body
        )
    )

    private fun cacheComments(newComments: List<Comment>) = newComments.forEach { newComment ->
        if (!commentsCache.contains(newComment)) {
            commentsCache.add(newComment)
        }
    }
}