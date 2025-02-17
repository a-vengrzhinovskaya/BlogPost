package com.example.blogpost.data.network.models.posts


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val records: List<Record>
) {
    @Serializable
    data class Record(
        @SerialName("fields")
        val post: Post,
        val id: String = ""
    ) {
        @Serializable
        data class Post(
            val authorId: List<String>,
            val body: String,
            val commentsIds: List<String>? = null,
            val date: String,
            val imageUrl: String,
            val title: String,
            val usersLikedIds: List<String>? = null
        )
    }
}