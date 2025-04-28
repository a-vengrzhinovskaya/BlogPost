package com.example.blogpost.domain.comments

import com.example.blogpost.domain.users.UsersRepository

class CreateCurrentUserCommentUseCase(
    private val usersRepository: UsersRepository,
    private val commentsRepository: CommentsRepository,
) {
    suspend operator fun invoke(postId: String, body: String) = try {
        val user = usersRepository.getCurrentUser()
            ?: error("Not authorized")
        commentsRepository.createComment(
            postId = postId,
            authorId = user.id,
            date = System.currentTimeMillis().toString(),
            body = body
        )
    } catch (e: Exception) {
        throw e
    }
}