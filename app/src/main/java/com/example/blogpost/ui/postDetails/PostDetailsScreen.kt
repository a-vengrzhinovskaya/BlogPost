package com.example.blogpost.ui.postDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.ExtraLargeSpacer
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.postDetails.components.CommentItem
import com.example.blogpost.ui.postDetails.components.CommentTextField
import com.example.blogpost.ui.postDetails.components.PostDetailsCard
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp
import org.koin.androidx.compose.koinViewModel

class PostDetailsScreen(private val postId: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<PostDetailsViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchPostDetails(postId)
        }

        Scaffold(
            content = { paddingValues ->
                PostDetailsBody(
                    paddingValues = paddingValues,
                    state = state,
                    onLikeClick = remember { { viewModel.onPostLike() } },
                    onCommentValueChange = remember { viewModel::onCurrentUserCommentChange },
                    onSendClick = remember { { viewModel.sendComment() } }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostDetailsBody(
    paddingValues: PaddingValues,
    state: PostDetailsScreenState,
    onLikeClick: () -> Unit,
    onCommentValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val screenState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp)
    ) {
        PostDetailsCard(
            post = state.post,
            onLikeClick = onLikeClick,
            onCommentClick = remember { { showBottomSheet = true } }
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = remember { { showBottomSheet = false } },
                sheetState = screenState
            ) {
                Column(modifier = Modifier.padding(extraLargeDp)) {
                    Text(text = "Комментарии:")

                    ExtraLargeSpacer()
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

                    SmallSpacer()
                    CommentTextField(
                        value = state.currentUserComment,
                        labelText = "Комментарий",
                        onValueChange = onCommentValueChange,
                        trailingIconId = R.drawable.ic_send,
                        onTrailingIconClick = onSendClick
                    )
                }
            }
        }
    }
}