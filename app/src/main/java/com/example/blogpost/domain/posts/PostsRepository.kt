package com.example.blogpost.domain.posts

import com.example.blogpost.domain.posts.models.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<List<Post>>

    fun getPostById(id: String): Flow<Post>
}