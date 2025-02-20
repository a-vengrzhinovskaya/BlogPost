package com.example.blogpost.domain.posts

import com.example.blogpost.domain.posts.models.PostWithAuthor
import com.example.blogpost.domain.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetPostsWithAuthorUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(query: String): List<PostWithAuthor> =
        withContext(coroutineContext) {
            postsRepository.getPosts(query).first().map { post ->
                val author = usersRepository.getUserById(post.authorId).first()
                PostWithAuthor(post, author)
            }
        }
}