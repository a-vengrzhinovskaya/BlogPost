package com.example.blogpost.data.network.models.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    val users: List<UserRecord>
) {
    @Serializable
    data class UserRecord(
        @SerialName("_id")
        val id: String = "",
        val email: String,
        val password: String,
        val avatarUrl: String,
        val comments: List<String>? = null,
        val name: String,
        val posts: List<String>? = null,
        val postsLiked: List<String>? = null
    )
}