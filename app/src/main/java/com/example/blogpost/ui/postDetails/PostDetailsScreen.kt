package com.example.blogpost.ui.postDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.common.components.ExtraLargeSpacer
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.postDetails.components.CommentItem
import com.example.blogpost.ui.postDetails.components.PostDetailsCard
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp
import org.koin.androidx.compose.koinViewModel

class PostDetailsScreen(private val postId: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<PostDetailsViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.fetchPostDetails(postId)
        }

        Scaffold(
            content = { paddingValues ->
                PostDetailsBody(
                    paddingValues = paddingValues,
                    state = state,
                    onLikeClick = {}
                )
            }
        )
    }
}

@Composable
private fun PostDetailsBody(
    paddingValues: PaddingValues,
    state: PostDetailsScreenState,
    onLikeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp)
    ) {
        PostDetailsCard(
            post = state.post,
            onLikeClick = onLikeClick
        )

        ExtraLargeSpacer()

        Text(text = "Комментарии:")

        MediumSpacer()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(mediumDp)
        ) {
            items(state.commentsWithAuthor) {
                CommentItem(
                    comment = it.comment,
                    author = it.author
                )
            }
        }
    }
}