package com.example.blogpost.domain.posts

import com.example.blogpost.domain.users.UsersRepository

class LikePostUseCase(
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(postId: String): Boolean {
        val user = usersRepository.getCurrentUser()
            ?: return false
        return postsRepository.likePost(postId, user.id)
    }
}