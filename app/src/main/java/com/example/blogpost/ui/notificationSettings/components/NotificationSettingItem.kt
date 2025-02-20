package com.example.blogpost.ui.notificationSettings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.blogpost.ui.common.components.ExtraSmallSpacer
import com.example.blogpost.ui.common.components.PrimaryCard
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp

@Composable
fun NotificationSettingItem(
    settingName: String,
    settingDescription: String,
    isSettingEnabled: Boolean,
    onToggle: (String, Boolean) -> Unit,
) {
    PrimaryCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(extraLargeDp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = settingName)
                ExtraSmallSpacer()
                Text(
                    text = settingDescription,
                    style = TextStyle(fontSize = 12.sp), // TODO: to theme 
                    color = Color.Gray
                )
            }
            Spacer(Modifier.weight(1f))
            Switch(
                modifier = Modifier.size(mediumDp),
                checked = isSettingEnabled,
                onCheckedChange = { onToggle(settingName, !isSettingEnabled) }
            )
        }
    }
}