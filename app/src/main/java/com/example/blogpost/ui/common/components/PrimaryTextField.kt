package com.example.blogpost.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.blogpost.ui.theme.BlogPostTheme
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = RoundedCornerShape(mediumDp),
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        singleLine = isSingleLine,
        label = { Text(text = labelText) }
    )
}

@Composable
@Preview
private fun PrimaryTextFieldPreview() {
    BlogPostTheme {
        PrimaryTextField(value = "ofododododod", onValueChange = {}, labelText = "email")
    }
}