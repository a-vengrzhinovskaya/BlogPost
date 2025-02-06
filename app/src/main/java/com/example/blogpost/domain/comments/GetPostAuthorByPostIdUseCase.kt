package com.example.blogpost.domain.comments

import com.example.blogpost.domain.posts.PostsRepository

class GetCommentsByPostIdUseCase(
    private val commentsRepository: CommentsRepository,
    private val postsRepository: PostsRepository
) {
    operator fun invoke(authorId: String) {

    }
}