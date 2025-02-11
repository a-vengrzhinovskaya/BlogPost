package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.blogpost.ui.feed.models.PostWithAuthorUI
import kotlinx.coroutines.launch

@Composable
fun PagerWithTabs(
    modifier: Modifier = Modifier,
    tabLabels: List<String>,
    allPosts: List<PostWithAuthorUI>,
    myPosts: List<PostWithAuthorUI>,
    onPostClick: (String) -> Unit,
    currentUserPostsNotFoundMessage: String
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { tabLabels.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        tabIndex = pagerState.currentPage
    }

    TabRow(
        modifier = modifier,
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[tabIndex])
            )
        }
    ) {
        tabLabels.forEachIndexed { index, title ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = title) }
            )
        }
    }
    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        state = pagerState
    ) {
        when (pagerState.currentPage) {
            0 -> {
                PostsList(
                    posts = allPosts,
                    onPostClick = onPostClick
                )
            }

            1 -> {
                PostsList(
                    posts = myPosts,
                    onPostClick = onPostClick,
                    notFoundMessage = currentUserPostsNotFoundMessage
                )
            }
        }
    }
}