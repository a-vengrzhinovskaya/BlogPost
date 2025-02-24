package com.example.blogpost.ui.singUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.blogpost.ui.common.components.PasswordTextField
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.common.components.PrimaryTextField
import com.example.blogpost.ui.common.components.PrimaryTopBar
import com.example.blogpost.ui.feed.FeedScreen
import com.example.blogpost.ui.theme.BlogPostTheme
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class SingUpScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SignUpViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(state.isSignUpComplete) {
            if (state.isSignUpComplete) navigator.replaceAll(FeedScreen())
        }

        Scaffold(
            topBar = {
                PrimaryTopBar(
                    text = "Регистрация",
                    onBackClick = remember { { navigator.pop() } }
                )
            },
            content = { paddingValues ->
                SignUpScreenBody(
                    paddingValues,
                    state,
                    onNameValueChange = remember { { viewModel.onNameValueChange(it) } },
                    onEmailValueChange = remember { { viewModel.onEmailValueChange(it) } },
                    onPasswordValueChange = remember { { viewModel.onPasswordValueChange(it) } },
                    onPasswordConfirmationValueChange = remember {
                        { viewModel.onConfirmPasswordValueChange(it) }
                    },
                    onSignUpClick = remember { { viewModel.signUp() } }
                )
            }
        )
    }
}

@Composable
private fun SignUpScreenBody(
    paddingValues: PaddingValues,
    state: SignUpScreenState,
    onNameValueChange: (String) -> Unit,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onPasswordConfirmationValueChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(extraLargeDp),
        verticalArrangement = Arrangement.spacedBy(largeDp)
    ) {
        PrimaryTextField(
            value = state.name,
            onValueChange = onNameValueChange,
            labelText = "Имя"
        )
        PrimaryTextField(
            value = state.email,
            onValueChange = onEmailValueChange,
            labelText = "Email"
        )
        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordValueChange,
            labelText = "Пароль"
        )
        PasswordTextField(
            value = state.confirmPassword,
            onValueChange = onPasswordConfirmationValueChange,
            labelText = "Подтвердите пароль"
        )
        PrimaryButton(
            text = "Зарегистрироваться",
            onClick = onSignUpClick
        )
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    BlogPostTheme {
        SignUpScreenBody(
            paddingValues = PaddingValues(),
            state = SignUpScreenState(
                name = "User",
                email = "user123@ya.ru",
                password = "User123",
                confirmPassword = "User123"
            ),
            onNameValueChange = {},
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onPasswordConfirmationValueChange = {},
            onSignUpClick = {}
        )
    }
}