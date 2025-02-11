package com.example.blogpost.domain.users

import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.posts.models.PostWithAuthor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetCurrentUserPostsUseCase(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(): List<PostWithAuthor> =
        withContext(coroutineContext) {
            val postsWithAuthor = mutableListOf<PostWithAuthor>()
            val currentUser = usersRepository.getCurrentUser().last()
            currentUser?.postsIds?.forEach {
                postsWithAuthor.add(
                    PostWithAuthor(
                        post = postsRepository.getPostById(it).last(),
                        author = currentUser
                    )
                )
            }
            postsWithAuthor
        }
}