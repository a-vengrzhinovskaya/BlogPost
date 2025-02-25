package com.example.blogpost.domain.posts

import com.example.blogpost.domain.posts.models.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(query: String, needToUpdate: Boolean = false): Flow<List<Post>>

    fun getPostById(id: String, needToUpdate: Boolean = false): Flow<Post>

    suspend fun createPost(
        authorId: String,
        date: String,
        title: String,
        body: String,
        imageUrl: String
    )

    suspend fun likePost(postId: String, userId: String): Boolean
}