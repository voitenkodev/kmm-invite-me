package com.voitenko.dev.kmminviteme.android.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.voitenko.dev.kmminviteme.android.common.base.Description
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme

@Composable
fun ErrorBlock(
    modifier: Modifier = Modifier,
    text: String,
    isShowed: Boolean
) = AnimatedVisibility(visible = isShowed) {
    Description(
        modifier = modifier,
        text = text,
        color = AppTheme.colors.errorColor
    )
}