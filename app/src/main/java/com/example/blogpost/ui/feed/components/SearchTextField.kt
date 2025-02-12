package com.example.blogpost.ui.feed.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    leadingIconId: Int,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = RoundedCornerShape(mediumDp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = labelText) },
        leadingIcon = { // TODO: Modifier.size
            Icon(
                painter = painterResource(id = leadingIconId),
                contentDescription = null
            )
        }
    )
}