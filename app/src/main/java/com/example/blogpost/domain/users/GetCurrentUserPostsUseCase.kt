package com.example.blogpost.domain.users

import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.posts.models.PostWithAuthor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetCurrentUserPostsUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(query: String): List<PostWithAuthor> =
        withContext(coroutineContext) {
            val currentUser = usersRepository.getCurrentUser()
            currentUser?.postsIds?.map {
                PostWithAuthor(
                    post = postsRepository.getPostById(it).first(),
                    author = currentUser
                )
            } ?: emptyList<PostWithAuthor>()
                .let { it ->
                    it.filter { it.post.title.contains(query, ignoreCase = true) }
                }
        }
}