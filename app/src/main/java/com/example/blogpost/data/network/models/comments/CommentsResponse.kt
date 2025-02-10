package com.example.blogpost.data.network.models.comments


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsResponse(
    val records: List<Record>
) {
    @Serializable
    data class Record(
        @SerialName("fields")
        val comment: CommentResponse,
        val id: String
    ) {
        @Serializable
        data class CommentResponse(
            val author: List<String>,
            val body: String,
            val id: Int,
            val date: String,
            val post: List<String>
        )
    }
}