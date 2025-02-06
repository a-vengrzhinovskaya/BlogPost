package com.example.blogpost.ui.postDetails

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.common.models.post.toUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailsViewModel(
    private val postsRepository: PostsRepository
) : StateViewModel<PostDetailsScreenState>(PostDetailsScreenState()) {
    fun fetchPostDetails(postId: String) {
        viewModelScope.launch {
            postsRepository.getPostById(postId).collectLatest { post ->
                mutableState.update {
                    it.copy(post = post.toUI())
                }
            }
        }
    }
}