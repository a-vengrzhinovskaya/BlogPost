package com.example.blogpost.domain.posts

import com.example.blogpost.domain.posts.models.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(query: String): Flow<List<Post>>

    fun getPostById(id: String): Flow<Post>

    suspend fun createPost(
        authorId: String,
        date: String,
        title: String,
        body: String,
        imageUrl: String
    )
}