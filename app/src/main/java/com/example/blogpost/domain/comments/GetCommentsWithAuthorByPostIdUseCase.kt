package com.example.blogpost.domain.comments

import com.example.blogpost.domain.comments.models.CommentWithAuthor
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetCommentsWithAuthorByPostIdUseCase(
    private val commentsRepository: CommentsRepository,
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(
        postId: String,
        needToUpdate: Boolean = false
    ): List<CommentWithAuthor> =
        withContext(coroutineContext) {
            postsRepository.getPostById(postId, needToUpdate).first().commentsIds.map { commentId ->
                val comment = commentsRepository.getCommentById(commentId).first()
                CommentWithAuthor(
                    comment = comment,
                    author = usersRepository.getUserById(comment.authorId).first()
                )
            }
        }
}