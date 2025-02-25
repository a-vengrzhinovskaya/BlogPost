package com.example.blogpost.ui.postEditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.PrimaryTextField
import com.example.blogpost.ui.postEditor.components.PostEditorTopBar
import com.example.blogpost.ui.theme.BlogPostTheme
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class PostEditorScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<PostEditorViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(state.isEditingComplete) {
            if (state.isEditingComplete) navigator.pop()
        }

        Scaffold(
            topBar = {
                PostEditorTopBar(
                    onBackClick = remember { { viewModel.savePostDraft() } },
                    onSaveClick = remember { { viewModel.publishPost() } },
                    onDeleteClick = remember { { viewModel.deletePost() } }
                )
            },
            content = { paddingValues ->
                PostEditorScreenBody(
                    paddingValues = paddingValues,
                    state = state,
                    onTitleValueChange = viewModel::onTitleValueChange,
                    onBodyValueChange = viewModel::onBodyValueChange,
                    onAttachedImageUrlValueChange = viewModel::onAttachedImageUrlValueChange
                )
            }
        )
    }
}

@Composable
private fun PostEditorScreenBody(
    paddingValues: PaddingValues,
    state: PostEditorScreenState,
    onTitleValueChange: (String) -> Unit,
    onBodyValueChange: (String) -> Unit,
    onAttachedImageUrlValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(extraLargeDp)
    ) {
        PrimaryTextField(
            value = state.title,
            labelText = "Заголовок",
            isSingleLine = false,
            onValueChange = onTitleValueChange
        )
        LargeSpacer()
        PrimaryTextField(
            modifier = Modifier.weight(1f),
            value = state.body,
            labelText = "Расскажите что-нибудь",
            isSingleLine = false,
            onValueChange = onBodyValueChange
        )
    }
}

@Composable
@Preview
fun PostEditorScreenPreview() {
    val state = PostEditorScreenState(
        title = "The Ultimate Guide to Understanding Your Feline Friend: All About Cats",
        body = "Cats are such delightful creatures! They're independent, curious, and make great companions. Whether they're lounging in a sunbeam or playing with a toy, they always bring joy to our lives. Who wouldn't love these adorable furballs?"
    )
    BlogPostTheme {
        PostEditorScreenBody(
            paddingValues = PaddingValues(),
            state = state,
            onTitleValueChange = {},
            onBodyValueChange = {},
            onAttachedImageUrlValueChange = {}
        )
    }
}