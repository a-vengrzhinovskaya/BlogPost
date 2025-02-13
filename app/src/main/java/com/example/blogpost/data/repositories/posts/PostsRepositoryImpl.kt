package com.example.blogpost.data.repositories.posts

import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.data.network.models.posts.PostsResponse
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
    private val postsCache = mutableListOf<Post>()

    override fun getPosts(query: String): Flow<List<Post>> = flow {
        val posts = api.getAllPosts().records.map {
            it.toDomain()
        }
        cachePosts(posts)
        emit(posts.filter { it.title.contains(query, ignoreCase = true) })
    }.flowOn(coroutineContext)

    override fun getPostById(id: String): Flow<Post> = flow {
        val post =
            postsCache.firstOrNull { it.id == id } ?: api.getPostById(id).toDomain()
        emit(post)
    }.flowOn(coroutineContext)

    override fun createPost(
        authorId: String,
        date: String,
        title: String,
        body: String,
        imageUrl: String
    ) = flow {
        val post = api.createPost(
            PostsResponse(
                records = listOf(
                    PostsResponse.Record(
                        post = PostsResponse.Record.Post(
                            authorId = listOf(authorId),
                            date = date,
                            title = title,
                            body = body,
                            imageUrl = imageUrl
                        )
                    )
                )
            )
        )
        emit(post.records.first().toDomain())
    }.flowOn(coroutineContext)

    private fun cachePosts(newPosts: List<Post>) = newPosts.forEach { newPost ->
        if (!postsCache.contains(newPost)) {
            postsCache.add(newPost)
        }
    }
}