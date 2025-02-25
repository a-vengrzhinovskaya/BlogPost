package com.example.blogpost.data.network

import com.example.blogpost.data.network.models.comments.CommentsResponse
import com.example.blogpost.data.network.models.posts.PostsResponse
import com.example.blogpost.data.network.models.users.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BlogPostAPI {
    //Users
    @GET("users")
    suspend fun getAllUsers(): UsersResponse

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UsersResponse.UserRecord

    @POST("users")
    suspend fun createUser(@Body data: UsersResponse.UserRecord)

    //Posts
    @GET("posts")
    suspend fun getAllPosts(): PostsResponse

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: String): PostsResponse.PostRecord

    @POST("posts")
    suspend fun createPost(@Body data: PostsResponse.PostRecord)

    //Comments
    @GET("comments/{id}")
    suspend fun getCommentById(@Path("id") id: String): CommentsResponse.CommentRecord

    @POST("comments")
    suspend fun createComment(@Body data: CommentsResponse.CommentRecord)

    @PATCH("posts/{id}/like/{userId}")
    suspend fun likePost(@Path("postId") postId: String, @Path("userId") userId: String)
}