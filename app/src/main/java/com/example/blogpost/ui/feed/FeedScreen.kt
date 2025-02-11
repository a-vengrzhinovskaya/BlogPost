package com.example.blogpost.ui.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.feed.components.FeedTopBar
import com.example.blogpost.ui.feed.components.PagerWithTabs
import com.example.blogpost.ui.postDetails.PostDetailsScreen
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
            topBar = {
                FeedTopBar(
                    query = state.query,
                    onQueryValueChange = remember { viewModel::onQueryValueChange },
                )
            },
            content = { paddingValues ->
                FeedScreenBody(
                    paddingValues = paddingValues,
                    state = state,
                    onPostClick = remember { { navigator.push(PostDetailsScreen(it)) } }
                )
            }
        )
    }
}

@Composable
private fun FeedScreenBody(
    paddingValues: PaddingValues,
    state: FeedScreenState,
    onPostClick: (String) -> Unit
) {
    val tabLabels by remember { mutableStateOf(listOf("Все", "Мои")) } // TODO: string recource 

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp),
    ) {
        PagerWithTabs(
            modifier = Modifier.fillMaxWidth(),
            tabLabels = tabLabels,
            allPosts = state.postsWithAuthor,
            myPosts = state.currentUserPosts,
            onPostClick = onPostClick,
            currentUserPostsNotFoundMessage = if (state.isAuthorized) {
                "Вы еще не создавали посты"
            } else {
                "Авторизуйтесь, чтобы просматривать свои посты"
            }
        )
    }
}