package com.example.blogpost.domain.posts

import com.example.blogpost.domain.users.UsersRepository

class PublishCurrentUserPostUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(title: String, body: String) = try {
        val user = usersRepository.getCurrentUser()
            ?: error("Not authorized")
        postsRepository.createPost(
            authorId = user.id,
            date = System.currentTimeMillis().toString(),
            title = title,
            body = body,
            imageUrl = "https://cataas.com/cat?time=${System.currentTimeMillis()}" // TODO: implement image upload
        )
    } catch (e: Exception) {
        throw e
    }
}