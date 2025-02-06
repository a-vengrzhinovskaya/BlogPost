package com.example.blogpost.ui.postDetails

import androidx.lifecycle.viewModelScope
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
    private val getCommentsWithAuthorByPostIdUseCase: GetCommentsWithAuthorByPostIdUseCase
) : StateViewModel<PostDetailsScreenState>(PostDetailsScreenState()) {
    fun fetchPostDetails(postId: String) {
        viewModelScope.launch {
            postsRepository.getPostById(postId).collectLatest { post ->
                val commentsWithAuthor = getCommentsWithAuthorByPostIdUseCase.invoke(post.id)
                mutableState.update {
                    it.copy(
                        post = post.toUI(),
                        commentsWithAuthor = commentsWithAuthor.map { it.toUI() }
                    )
                }
            }
        }
    }
}