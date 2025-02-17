package com.example.blogpost.data.network.models.users


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    val records: List<Record>
) {
    @Serializable
    data class Record(
        @SerialName("fields")
        val user: User,
        val id: String = ""
    ) {
        @Serializable
        data class User(
            val email: String,
            val password: String,
            val avatarUrl: String,
            val comments: List<String>? = null,
            val name: String,
            val posts: List<String>? = null,
            val postsLiked: List<String>? = null
        )
    }
}