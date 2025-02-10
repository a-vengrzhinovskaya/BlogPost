package com.example.blogpost.ui.postDetails.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun CommentTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    trailingIcon: Int,
    onTrailingIconClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = RoundedCornerShape(mediumDp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        trailingIcon = {
            IconButton(
                enabled = value.isNotBlank(),
                onClick = onTrailingIconClick
            ) {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null
                )
            }
        }
    )
}