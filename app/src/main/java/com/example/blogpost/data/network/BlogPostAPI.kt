package com.example.blogpost.data.network

import com.example.blogpost.data.network.models.comments.CommentsResponse
import com.example.blogpost.data.network.models.posts.PostsResponse
import com.example.blogpost.data.network.models.users.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogPostAPI {
    //Users
    @GET("Users")
    suspend fun getAllUsers(): UsersResponse //TODO: не нужно?

    @GET("Users/{id}")
    suspend fun getUserById(@Path("id") id: String): UsersResponse.Record

    //Posts
    @GET("Posts")
    suspend fun getAllPosts(): PostsResponse

    @GET("Posts/{id}")
    suspend fun getPostById(@Path("id") id: String): PostsResponse.Record //TODO: не нужно?

    //Comments
    @GET("Comments")
    suspend fun getAllComments(): CommentsResponse //TODO: не нужно?

    @GET("Comments/{id}")
    suspend fun getCommentById(@Path("id") id: String): CommentsResponse.Record
}