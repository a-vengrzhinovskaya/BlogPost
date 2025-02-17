package com.example.blogpost.domain.comments

import com.example.blogpost.domain.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class CreateCurrentUserCommentUseCase(
    private val usersRepository: UsersRepository,
    private val commentsRepository: CommentsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    operator fun invoke(postId: String, body: String) = flow {
        val user = usersRepository.getCurrentUser().first()
            ?: error("Not authorized")
        commentsRepository.createComment(
            postId = postId,
            authorId = user.id,
            date = System.currentTimeMillis().toString(),
            body = body
        )
        emit(Unit)
    }.flowOn(coroutineContext)
}