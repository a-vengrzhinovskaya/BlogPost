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
                name = "Cats",
                date = "04.02.2025",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlVTWL4dqk9ZokaiRQe0kdV3_dNvkB7A3xTw&s",
                author = AuthorUI(
                    name = "Cats",
                    avatarImageUrl = "https://www.cdc.gov/healthy-pets/media/images/2024/04/Cat-on-couch.jpg"
                ),
                text = "meow meow meow meow",
                likesCount = 10,
                commentsCount = 5
            )
        }
        mutableState.update { it.copy(posts = posts) }
    }
}