package com.example.blogpost.ui.postEditor

import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update

class PostEditorViewModel(
    private val postsRepository: PostsRepository
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

    fun savePost() {

    }

    fun deletePost() {

    }
}