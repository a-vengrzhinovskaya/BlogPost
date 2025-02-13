package com.example.blogpost.data.network

import com.example.blogpost.data.network.models.comments.CommentsResponse
import com.example.blogpost.data.network.models.posts.PostsResponse
import com.example.blogpost.data.network.models.users.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BlogPostAPI {
    //Users
    @GET("Users")
    suspend fun getAllUsers(): UsersResponse

    @GET("Users/{id}")
    suspend fun getUserById(@Path("id") id: String): UsersResponse.Record

    @POST
    suspend fun createUser(@Body data: UsersResponse): UsersResponse

    //Posts
    @GET("Posts")
    suspend fun getAllPosts(): PostsResponse

    @GET("Posts/{id}")
    suspend fun getPostById(@Path("id") id: String): PostsResponse.Record

    @POST("Posts")
    suspend fun createPost(@Body data: PostsResponse): PostsResponse

    //Comments
    @GET("Comments/{id}")
    suspend fun getCommentById(@Path("id") id: String): CommentsResponse.Record

    @POST("Comments")
    suspend fun createComment(@Body data: CommentsResponse): CommentsResponse
}