package com.voitenko.dev.kmminviteme.android.designsystem.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.voitenko.dev.kmminviteme.android.designsystem.neu

@Composable
fun FlatBox(
    modifier: Modifier,
    radius: Dp, color: Color,
    content: @Composable BoxScope.() -> Unit
) = Box(
    modifier = modifier
        .neu(radius, pressed = false)
        .background(color, RoundedCornerShape(radius)),
    contentAlignment = Alignment.CenterStart,
    content = content
)
