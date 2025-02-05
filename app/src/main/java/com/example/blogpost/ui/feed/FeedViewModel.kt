package com.example.blogpost.ui.feed

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.common.models.post.toUI
import com.example.blogpost.ui.common.models.user.toUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getPostsWithAuthorUseCase: GetPostsWithAuthorUseCase
) : StateViewModel<FeedScreenState>(FeedScreenState()) {
    fun fetchPosts() {
        viewModelScope.launch {
            getPostsWithAuthorUseCase.invoke().collectLatest { postsWithAuthor ->
                mutableState.update { feedScreenState ->
                    feedScreenState.copy(
                        postsWithAuthor = postsWithAuthor.map {
                            Pair(it.first.toUI(), it.second.toUI())
                        }
                    )
                }
            }
        }
    }
}