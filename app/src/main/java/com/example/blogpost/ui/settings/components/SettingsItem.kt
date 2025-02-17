package com.example.blogpost.ui.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.blogpost.R
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.common.components.SmallSpacer
import com.example.blogpost.ui.theme.extraLargeDp

@Composable
fun SettingsItem(
    settingName: String,
    settingIconId: Int,
    navigatesToScreen: Boolean = false,
    onSettingClick: () -> Unit
) {
    PrimaryCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(extraLargeDp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(settingIconId),
                tint = Color.Gray,
                contentDescription = null
            )
            SmallSpacer()
            Text(
                text = settingName
            )
            if (navigatesToScreen) {
                Spacer(Modifier.weight(1f))
                IconButton(onSettingClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        tint = Color.Gray,
                        contentDescription = null
                    )
                }
            }
        }
    }
}