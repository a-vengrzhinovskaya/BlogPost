package com.example.blogpost.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.SubcomposeAsyncImage
import com.example.blogpost.ui.common.components.ExtraLargeSpacer
import com.example.blogpost.ui.common.components.PrimaryTextField
import com.example.blogpost.ui.common.components.PrimaryTopBar
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) { viewModel.fetchUser() }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                PrimaryTopBar(
                    text = "Профиль",
                    onBackClick = remember { { navigator.pop() } }
                )
            },
            content = { paddingValues ->
                ProfileScreenBody(
                    paddingValues = paddingValues,
                    state = state
                )
            }
        )
    }
}

@Composable
private fun ProfileScreenBody(
    paddingValues: PaddingValues,
    state: ProfileScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape),
            model = state.avatarUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.name,
            labelText = "Имя",
            isEnabled = false,
            onValueChange = {}
        )
        SmallSpacer()
        PrimaryTextField(
            value = state.email,
            labelText = "Email",
            isEnabled = false,
            onValueChange = {}
        )
    }
}