package com.voitenko.dev.kmminviteme.android.superbox

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

enum class RotationType { X, Y, Z }

@Composable
fun FlipperBox(
    modifier: Modifier = Modifier,
    rotationType: RotationType,
    degree: Float = 360f,
    frontContent: @Composable (() -> Unit) -> Unit,
    backContent: @Composable (() -> Unit) -> Unit,
) {

    val innerState = rememberSaveable { mutableStateOf(true) }

    val rotation by animateFloatAsState(
        if (innerState.value) 0f else degree, spring(stiffness = Spring.StiffnessLow)
    )
    Box(
        modifier = modifier
            .graphicsLayer {
                if (rotationType == RotationType.Y) rotationY = rotation
                if (rotationType == RotationType.X) rotationX = rotation
                if (rotationType == RotationType.Z) rotationZ = rotation
                cameraDistance = 8 * density
            }
    ) {
        if (rotation <= 180) frontContent.invoke {
            innerState.value = !innerState.value
        } else backContent.invoke {
            innerState.value = !innerState.value
        }
    }
}