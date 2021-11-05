package com.voitenko.dev.kmminviteme.android.features.warning

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.voitenko.dev.kmminviteme.android.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.base.Description

@Composable
fun Error(
    modifier: Modifier = Modifier,
    state: ErrorFeature.State,
) = AnimatedVisibility(visible = state.isShowed) {
    Description(
        modifier = modifier,
        text = state.text,
        color = AppTheme.colors.errorColor
    )
}