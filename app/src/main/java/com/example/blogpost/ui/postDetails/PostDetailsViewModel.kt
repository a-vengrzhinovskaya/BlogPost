package com.example.blogpost.ui.postDetails

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.comments.CreateCurrentUserCommentUseCase
import com.example.blogpost.domain.comments.GetCommentsWithAuthorByPostIdUseCase
import com.example.blogpost.domain.posts.LikePostUseCase
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.common.models.posts.toUI
import com.example.blogpost.ui.postDetails.models.toUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailsViewModel(
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository,
    private val likePostUseCase: LikePostUseCase,
    private val getCommentsWithAuthorByPostIdUseCase: GetCommentsWithAuthorByPostIdUseCase,
    private val createCurrentUserCommentUseCase: CreateCurrentUserCommentUseCase
) : StateViewModel<PostDetailsScreenState>(PostDetailsScreenState()) {
    fun fetchPostDetails(postId: String, needToUpdate: Boolean = false) {
        viewModelScope.launch {
            postsRepository.getPostById(postId, needToUpdate).collectLatest { post ->
                val commentsWithAuthor = getCommentsWithAuthorByPostIdUseCase(post.id, needToUpdate)
                mutableState.update { postDetailsScreenState ->
                    val isLiked = checkIfPostIsLiked()
                    val postUI = post.toUI()
                    postDetailsScreenState.copy(
                        post = postUI,
                        commentsWithAuthor = commentsWithAuthor.map { it.toUI() },
                        isLiked = isLiked
                    )
                }
            }
        }
    }

    private suspend fun checkIfPostIsLiked() =
        usersRepository.getCurrentUser()?.postsLikedIds?.contains(state.value.post.id)
            ?: false

    fun onPostLike() {
        viewModelScope.launch {
            likePostUseCase(state.value.post.id)
            fetchPostDetails(postId = state.value.post.id, needToUpdate = true)
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
            fetchPostDetails(postId = state.value.post.id, needToUpdate = true)
        } catch (e: Exception) {
            Log.d("comment", "failed to send comment")
        }
    }
}