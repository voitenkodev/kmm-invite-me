package com.voitenko.dev.kmminviteme.android.common.base.box

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

enum class FlipIconType { X, Y, Z }

@Composable
fun FlipBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    type: List<FlipIconType>,
    repeatCount: Int = Int.MAX_VALUE,
    content: (@Composable () -> Unit)? = null,
) {

    val durationMillis = 1300
    val delayMillis = 0

    val rotationTransition = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(key1 = true) {
        rotationTransition.animateTo(
            targetValue = 360f, animationSpec = repeatable(
                iterations = repeatCount,
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                    delayMillis = delayMillis
                )
            )
        )
    }

    Box(
        modifier = modifier.graphicsLayer {
            if (type.contains(FlipIconType.Y)) rotationY = rotationTransition.value
            if (type.contains(FlipIconType.X)) rotationX = rotationTransition.value
            if (type.contains(FlipIconType.Z)) rotationZ = rotationTransition.value
            cameraDistance = 8 * density
        },
        contentAlignment = contentAlignment,
        content = { content?.invoke() }
    )
}