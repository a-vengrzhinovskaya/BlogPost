package com.example.blogpost.data.network.models.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsResponse(
    val comments: List<CommentRecord>
) {
    @Serializable
    data class CommentRecord(
        @SerialName("_id")
        val id: String = "",
        val authorId: String,
        val body: String,
        val date: String,
        val post: String
    )
}