package com.voitenko.dev.kmminviteme.android.old.common.base.box

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

enum class FlipType { X, Y, Z }

@Composable
fun FlipperContent(
    modifier: Modifier = Modifier,
    type: FlipType,
    flip: Boolean,
    frontContent: @Composable () -> Unit,
    backContent: @Composable () -> Unit,
) {

    val rotation by animateFloatAsState(
        if (flip) 0f else 360f, spring(stiffness = Spring.StiffnessLow)
    )
//    remember { mutableStateOf(rotation); }
    Box(
        modifier = modifier.graphicsLayer {
            if (type == FlipType.Y) rotationY = rotation
            if (type == FlipType.X) rotationX = rotation
            if (type == FlipType.Z) rotationZ = rotation
            cameraDistance = 8 * density
        }
    ) { if (rotation <= 180) frontContent.invoke() else backContent.invoke() }
}