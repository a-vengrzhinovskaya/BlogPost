package com.example.blogpost.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.auth.components.AuthTopBar
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.PasswordTextField
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.common.components.PrimaryTextField
import com.example.blogpost.ui.feed.FeedScreen
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class AuthScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AuthViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                AuthTopBar(
                    topBarText = "BlogPost",
                    topBarButtonText = "Продолжить без регистрации",
                    onTopBarButtonClick = remember { { navigator.push(FeedScreen()) } }
                )
            },
            content = { paddingValues ->
                AuthScreenBody(
                    state = state,
                    onEmailFieldValueChange = remember {
                        viewModel::onEmailFieldValueChange
                    },
                    onPasswordFieldValueChange = remember {
                        viewModel::onPasswordFieldValueChange
                    },
                    onRegisterClick = {},
                    onLoginClick = remember { { navigator.push(FeedScreen()) } },
                    paddingValues = paddingValues
                )
            }
        )
    }
}

@Composable
private fun AuthScreenBody(
    state: AuthScreenState,
    onEmailFieldValueChange: (String) -> Unit,
    onPasswordFieldValueChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(extraLargeDp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Авторизация",
            textAlign = TextAlign.Center
        )

        LargeSpacer()

        PrimaryTextField(
            value = state.email,
            labelText = "Электронная почта",
            onValueChange = onEmailFieldValueChange
        )

        LargeSpacer()

        PasswordTextField(
            value = state.password,
            labelText = "Пароль",
            onValueChange = onPasswordFieldValueChange
        )

        LargeSpacer()

        PrimaryButton(
            text = "Войти",
            onClick = onLoginClick
        )

        LargeSpacer()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onRegisterClick() },
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Нет аккаунта?")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(" Зарегистрироваться")
                }
            }
        )
    }
}