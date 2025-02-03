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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import com.example.blogpost.ui.auth.components.AuthTopBar
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.PasswordTextField
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.common.components.PrimaryTextField
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp

class AuthScreen : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                AuthTopBar(topBarText = "BlogPost",
                    topBarButtonText = "Продолжить без регистрации",
                    onTopBarButtonClick = {})
            },
            content = { paddingValues ->
                AuthScreenBody(paddingValues)
            }
        )
    }
}

@Composable
private fun AuthScreenBody(
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
            modifier = Modifier.fillMaxWidth(), text = "Авторизация", textAlign = TextAlign.Center
        )

        LargeSpacer()

        PrimaryTextField(value = "", labelText = "Электронная почта", onValueChange = {})

        LargeSpacer()

        PasswordTextField(value = "", labelText = "Пароль", onValueChange = {})

        LargeSpacer()

        PrimaryButton(text = "Войти", onClick = {})

        LargeSpacer()

        Text(modifier = Modifier
            .fillMaxWidth()
            .clickable { },
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Нет аккаунта?")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(" Зарегистрироваться")
                }
            })
    }
}