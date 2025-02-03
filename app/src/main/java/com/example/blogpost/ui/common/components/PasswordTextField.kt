package com.example.blogpost.ui.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.blogpost.R
import com.example.blogpost.ui.theme.BlogPostTheme
import com.example.blogpost.ui.theme.mediumDp
import com.example.blogpost.ui.theme.smallDp

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(mediumDp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) {
                R.drawable.ic_password_visibility_off
            } else {
                R.drawable.ic_password_visibility_on
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = painterResource(id = icon), null)
            }
        },
        singleLine = true,
        label = { Text(text = labelText) }
    )
    AnimatedVisibility(visible = errorMessage != null) {
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = smallDp)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PrimaryTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    BlogPostTheme {
        PasswordTextField(
            value = value,
            labelText = "Hint",
            onValueChange = { value = it }
        )
    }
}