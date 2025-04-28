package com.example.blogpost.data.network.models.posts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<PostRecord>
) {
    @Serializable
    data class PostRecord(
        @SerialName("_id")
        val id: String = "",
        val authorId: String,
        val body: String,
        val commentsIds: List<String>? = null,
        val date: String,
        val imageUrl: String,
        val title: String,
        val usersLikedIds: List<String>? = null
    )
}