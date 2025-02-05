package com.example.blogpost.domain.posts

import com.example.blogpost.domain.posts.models.Post
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetPostsWithAuthorUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository
) {
    operator fun invoke(): Flow<List<Pair<Post, User>>> = flow {
        postsRepository.getPosts().collectLatest {
            emit(it.map { post ->
                Pair(post, usersRepository.getUserById(post.authorId).first())
            })
        }
    }
}