package com.example.blogpost.ui.feed

import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.common.models.AuthorUI
import com.example.blogpost.ui.common.models.PostUI
import kotlinx.coroutines.flow.update

class FeedViewModel(

) : StateViewModel<FeedScreenState>(FeedScreenState()) {
    fun fetchPosts() {
        val posts = List(10) {
            PostUI(
                title = "Cats",
                date = "04.02.2025",
                imageUrl = "https://cataas.com/cat?" + (0..1_000_000).random(),
                author = AuthorUI(
                    name = "Cats",
                    avatarImageUrl = "https://www.cdc.gov/healthy-pets/media/images/2024/04/Cat-on-couch.jpg"
                ),
                body = "meow meow meow meow",
                likesCount = 10,
                commentsCount = 5
            )
        }
        mutableState.update { it.copy(posts = posts) }
    }
}