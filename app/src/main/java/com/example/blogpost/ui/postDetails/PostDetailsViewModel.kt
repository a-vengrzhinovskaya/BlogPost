package com.example.blogpost.ui.postDetails

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.comments.CreateCurrentUserCommentUseCase
import com.example.blogpost.domain.comments.GetCommentsWithAuthorByPostIdUseCase
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.common.models.posts.toUI
import com.example.blogpost.ui.postDetails.models.toUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailsViewModel(
    private val postsRepository: PostsRepository,
    private val getCommentsWithAuthorByPostIdUseCase: GetCommentsWithAuthorByPostIdUseCase,
    private val createCurrentUserCommentUseCase: CreateCurrentUserCommentUseCase
) : StateViewModel<PostDetailsScreenState>(PostDetailsScreenState()) {
    fun fetchPostDetails(postId: String) {
        viewModelScope.launch {
            postsRepository.getPostById(postId).collectLatest { post ->
                val commentsWithAuthor = getCommentsWithAuthorByPostIdUseCase(post.id)
                mutableState.update { postDetailsScreenState ->
                    postDetailsScreenState.copy(
                        post = post.toUI(),
                        commentsWithAuthor = commentsWithAuthor.map { it.toUI() }
                    )
                }
            }
        }
    }

    fun onPostLike() {
        mutableState.update {
            val isCurrentlyLiked = !it.isLiked
            it.copy(
                isLiked = isCurrentlyLiked,
                post = it.post.copy(
                    likesCount = if (isCurrentlyLiked) {
                        it.post.likesCount + 1
                    } else {
                        it.post.likesCount - 1
                    }
                )
            )
        }
    }

    fun onCurrentUserCommentChange(newValue: String) {
        mutableState.update {
            it.copy(currentUserComment = newValue)
        }
    }

    fun sendComment() = viewModelScope.launch {
        try {
            createCurrentUserCommentUseCase(
                state.value.post.id,
                state.value.currentUserComment
            )
            mutableState.update {
                it.copy(currentUserComment = "")
            }
            fetchPostDetails(state.value.post.id)
        } catch (e: Exception) {
            Log.d("comment", "failed to send comment")
        }
    }
}