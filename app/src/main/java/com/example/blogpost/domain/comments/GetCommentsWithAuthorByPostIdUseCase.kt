package com.example.blogpost.domain.comments

import com.example.blogpost.domain.comments.models.Comment
import com.example.blogpost.domain.comments.models.CommentWithAuthor
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetCommentsWithAuthorByPostIdUseCase(
    private val commentsRepository: CommentsRepository,
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(postId: String): List<CommentWithAuthor> =
        withContext(coroutineContext) {
            val comments = mutableListOf<Comment>()
            postsRepository.getPostById(postId).collectLatest { post ->
                post.commentsIds.forEach { commentId ->
                    commentsRepository.getCommentById(commentId).collectLatest { comment ->
                        comments.add(comment)
                    }
                }
            }

            val commentsWithAuthor = mutableListOf<CommentWithAuthor>()
            comments.forEach { comment ->
                usersRepository.getUserById(comment.authorId).collectLatest { user ->
                    commentsWithAuthor.add(CommentWithAuthor(comment, user))
                }
            }
            commentsWithAuthor
        }
}