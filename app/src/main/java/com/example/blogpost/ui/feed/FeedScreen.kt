package com.example.blogpost.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.feed.components.PostItem
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class FeedScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<FeedViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.fetchPosts()
        }

        Scaffold(
            content = { paddingValues ->
                FeedScreenBody(
                    paddingValues = paddingValues,
                    state = state
                )
            }
        )
    }
}

@Composable
private fun FeedScreenBody(
    paddingValues: PaddingValues,
    state: FeedScreenState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(extraLargeDp)
    ) {
        items(state.posts) { post ->
            PostItem(post)
        }
    }
}