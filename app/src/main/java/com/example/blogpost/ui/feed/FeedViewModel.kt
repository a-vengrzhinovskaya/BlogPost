package com.example.blogpost.ui.feed

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import com.example.blogpost.domain.users.GetCurrentUserPostsUseCase
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.feed.models.toUI
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val usersRepository: UsersRepository,
    private val getCurrentUserPostsUseCase: GetCurrentUserPostsUseCase,
    private val getPostsWithAuthorUseCase: GetPostsWithAuthorUseCase,
) : StateViewModel<FeedScreenState>(FeedScreenState()) {
    init {
        viewModelScope.launch {
            mutableState.update {
                it.copy(
                    isAuthorized = usersRepository.isAuthorized()
                )
            }
        }
    }

    fun fetchPosts(query: String) = viewModelScope.launch {
        val postsWithAuthor = getPostsWithAuthorUseCase(query)
        val currentUserPosts = getCurrentUserPostsUseCase(query)
        mutableState.update { feedScreenState ->
            feedScreenState.copy(
                postsWithAuthor = postsWithAuthor.map { it.toUI() },
                currentUserPosts = currentUserPosts.map { it.toUI() }
            )
        }
    }

    fun onQueryValueChange(newValue: String) {
        if (newValue.length > state.value.query.length || newValue.length < state.value.query.length)
            mutableState.update {
                it.copy(query = newValue)
            }
        fetchPosts(state.value.query)
    }
}