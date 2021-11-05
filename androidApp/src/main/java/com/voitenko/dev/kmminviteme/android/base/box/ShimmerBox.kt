package com.voitenko.dev.kmminviteme.android.base.box

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    gradient: List<Color>,
    content: (@Composable (Brush) -> Unit)? = null,
) = BoxWithConstraints(modifier = modifier) {

    val durationMillis = 1300
    val delayMillis = 0

    val cardWidthPx = with(LocalDensity.current) { (maxWidth).toPx() }
    val cardHeightPx = with(LocalDensity.current) { (maxHeight).toPx() }

    val gradientWidth: Float = (0.7f * cardHeightPx)
    val infiniteTransition = rememberInfiniteTransition()

    val xCardShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (cardWidthPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Restart,
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
                delayMillis = delayMillis
            ),
        )
    )
    val yCardShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (cardHeightPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Restart,
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
                delayMillis = delayMillis
            ),
        )
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(xCardShimmer.value - gradientWidth, yCardShimmer.value - gradientWidth),
        end = Offset(xCardShimmer.value, yCardShimmer.value)
    )
    content?.invoke(brush)
}
