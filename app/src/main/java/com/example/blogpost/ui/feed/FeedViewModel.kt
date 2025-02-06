package com.example.blogpost.ui.feed

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.feed.models.toUI
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getPostsWithAuthorUseCase: GetPostsWithAuthorUseCase
) : StateViewModel<FeedScreenState>(FeedScreenState()) {
    fun fetchPosts() {
        viewModelScope.launch {
            val postsWithAuthor = getPostsWithAuthorUseCase.invoke().map { it.toUI() }
            mutableState.update {
                it.copy(
                    postsWithAuthor = postsWithAuthor
                )
            }
        }
    }
}