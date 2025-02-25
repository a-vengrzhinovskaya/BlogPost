package com.example.blogpost.ui.postEditor

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.posts.PublishCurrentUserPostUseCase
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostEditorViewModel(
    private val postsRepository: PostsRepository,
    private val publishCurrentUserPostUseCase: PublishCurrentUserPostUseCase
) : StateViewModel<PostEditorScreenState>(PostEditorScreenState()) {
    fun onTitleValueChange(newValue: String) = mutableState.update {
        it.copy(title = newValue)
    }

    fun onBodyValueChange(newValue: String) = mutableState.update {
        it.copy(body = newValue)
    }

    fun onAttachedImageUrlValueChange(newValue: String) = mutableState.update {
        it.copy(attachedImageUrl = newValue)
    }

    fun publishPost() = viewModelScope.launch {
        try {
            publishCurrentUserPostUseCase(state.value.title, state.value.body)
            mutableState.update { it.copy(isEditingComplete = true) }
        } catch (e: Exception) {
            Log.d("publish", e.message.toString())
        }
    }

    fun deletePost() {
        // TODO: implement
        mutableState.update { it.copy(isEditingComplete = true) }
    }

    fun savePostDraft() {
        // TODO: implement saving to local db
        mutableState.update { it.copy(isEditingComplete = true) }
    }
}