package com.example.blogpost.ui.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.extraSmallDp
import com.example.blogpost.ui.theme.largeDp
import com.example.blogpost.ui.theme.mediumDp
import com.example.blogpost.ui.theme.smallDp

@Composable
fun ExtraSmallSpacer() = Spacer(modifier = Modifier.size(extraSmallDp))

@Composable
fun SmallSpacer() = Spacer(modifier = Modifier.size(smallDp))

@Composable
fun MediumSpacer() = Spacer(modifier = Modifier.size(mediumDp))

@Composable
fun LargeSpacer() = Spacer(modifier = Modifier.size(largeDp))

@Composable
fun ExtraLargeSpacer() = Spacer(modifier = Modifier.size(extraLargeDp))