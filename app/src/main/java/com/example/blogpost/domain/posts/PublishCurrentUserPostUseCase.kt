package com.example.blogpost.domain.posts

import com.example.blogpost.domain.users.UsersRepository

class PublishCurrentUserPostUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(title: String, body: String) {
        val user = usersRepository.getCurrentUser().first()
        postsRepository.createPost(user.id, "", title, body, "").first()
    }
}