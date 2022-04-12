package com.voitenko.dev.kmminviteme.android.designsystem.components.box

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FlipBox(
    modifier: Modifier = Modifier,
    degree: Float = 180f,
    frontContent: @Composable () -> Unit,
    backContent: @Composable () -> Unit,
    flip: Boolean
) {
    val rotation by animateFloatAsState(
        if (flip) 0f else degree, spring(stiffness = Spring.StiffnessLow)
    )

    Box(
        modifier = modifier.graphicsLayer {
            rotationY = rotation
            cameraDistance = 8 * density
        },
        content = {
            if (rotation <= 90) frontContent.invoke()
            else backContent.invoke()
        })
}