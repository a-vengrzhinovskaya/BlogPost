package com.example.blogpost.ui.feed

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.feed.components.FeedBottomBar
import com.example.blogpost.ui.feed.components.FeedTopBar
import com.example.blogpost.ui.feed.components.PagerWithTabs
import com.example.blogpost.ui.postDetails.PostDetailsScreen
import com.example.blogpost.ui.postEditor.PostEditorScreen
import com.example.blogpost.ui.settings.SettingsMenuScreen
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

private const val DEFAULT_QUERY = ""

class FeedScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<FeedViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val pullToRefreshState = rememberPullToRefreshState()
        var isRefreshing by remember { mutableStateOf(false) }

        val onRefresh: () -> Unit = remember {
            {
                isRefreshing = true
                viewModel.fetchPosts(query = DEFAULT_QUERY, needToUpdate = true)
                isRefreshing = false
            }
        }

        LaunchedEffect(Unit) { viewModel.fetchPosts(DEFAULT_QUERY) }

        LaunchedEffect(state.query) { viewModel.fetchPosts(state.query) } // TODO: make cooldown

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                FeedTopBar(
                    query = state.query,
                    onQueryValueChange = remember { viewModel::onQueryValueChange }
                )
            },
            content = { paddingValues ->
                FeedScreenBody(
                    paddingValues = paddingValues,
                    pullToRefreshState = pullToRefreshState,
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    state = state,
                    onPostClick = remember { { navigator.push(PostDetailsScreen(it)) } }
                )
            },
            bottomBar = {
                FeedBottomBar(
                    onFeedButtonClick = remember { { navigator.push(FeedScreen()) } },
                    onAddButtonClick =
                    if (state.isAuthorized) {
                        remember { { navigator.push(PostEditorScreen()) } }
                    } else {
                        remember {
                            {
                                Toast.makeText(
                                    context,
                                    "Войдите, чтобы создавать посты",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    },
                    onSettingsButtonClick = remember { { navigator.push(SettingsMenuScreen()) } }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedScreenBody(
    paddingValues: PaddingValues,
    pullToRefreshState: PullToRefreshState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    state: FeedScreenState,
    onPostClick: (String) -> Unit,
) {
    val tabLabels by remember { mutableStateOf(listOf("Все", "Мои")) } // TODO: string resource 

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = pullToRefreshState,
        onRefresh = onRefresh
    ) {
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
}