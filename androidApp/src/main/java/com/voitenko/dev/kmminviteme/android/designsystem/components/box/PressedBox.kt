package com.voitenko.dev.kmminviteme.android.designsystem.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.voitenko.dev.kmminviteme.android.designsystem.neu

@Composable
fun PressedBox(
    modifier: Modifier, radius: Dp, color: Color, content: @Composable BoxScope.() -> Unit
) = Box(modifier = modifier.background(color, RoundedCornerShape(radius)).clip(RoundedCornerShape(radius))) {
    content.invoke(this)
    Spacer(modifier = modifier
        .neu(radius, pressed = true)
        .fillMaxSize())
}