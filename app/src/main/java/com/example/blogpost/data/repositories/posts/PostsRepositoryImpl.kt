package com.example.blogpost.data.repositories.posts

import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.posts.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class PostsRepositoryImpl(
    private val api: BlogPostAPI,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : PostsRepository {
    override fun getPosts(): Flow<List<Post>> = flow {
        val posts = api.getAllPosts().records.map {
            it.toDomain()
        }
        emit(posts)
    }.flowOn(coroutineContext)

    override fun getPostById(id: String): Flow<Post> = flow {
        val post = api.getPostById(id).toDomain()
        emit(post)
    }.flowOn(coroutineContext)
}