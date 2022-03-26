package com.voitenko.dev.kmminviteme.android.superbox

import android.view.MotionEvent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val maxAngle = 50f
private val maxTranslation = 140.dp.value

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun ParallaxBox(
    topLevelContent: @Composable BoxScope.() -> Unit,
    bottomLevelContent: @Composable BoxScope.() -> Unit
) {

    var angle by remember { mutableStateOf(Pair(0f, 0f)) }
    var start by remember { mutableStateOf(Pair(-1f, -1f)) }
    var viewSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = Modifier
        .onGloballyPositioned { coordinates ->
            viewSize = Size(
                width = coordinates.size.width.toFloat(),
                height = coordinates.size.height.toFloat()
            )
        }
        .pointerInteropFilter { m ->
            when (m.action) {
                MotionEvent.ACTION_UP -> {
                    start = Pair(-1f, -1f)
                }
                MotionEvent.ACTION_DOWN -> {
                    start = Pair(m.rawX, m.rawY)
                }
                MotionEvent.ACTION_MOVE -> {
                    if (viewSize != Size.Zero) {
                        val end = Pair(m.rawX, m.rawY)
                        val newAngle = getRotationAngles(start, end, viewSize)
                        var x: Float = angle.first + newAngle.first
                        var y: Float = angle.second + newAngle.second
                        if (x > maxAngle) x = maxAngle
                        else if (x < -maxAngle) x = -maxAngle
                        if (y > maxAngle) y = maxAngle
                        else if (y < -maxAngle) y = -maxAngle
                        angle = Pair(x, y)
                        start = end
                    }
                }
            }
            true
        }) {
        Box(
            modifier = Modifier.graphicsLayer(
                transformOrigin = TransformOrigin(0.5f, 0.5f),
                rotationY = animateFloatAsState(-angle.first).value,
                rotationX = animateFloatAsState(angle.second).value,
                cameraDistance = 16.dp.value
            ), content = bottomLevelContent
        )
        Box(
            modifier = Modifier.graphicsLayer(
                transformOrigin = TransformOrigin(0.5f, 0.5f),
                rotationY = animateFloatAsState(-angle.first).value,
                rotationX = animateFloatAsState(angle.second).value,
                cameraDistance = 16.dp.value,
                translationX = -getTranslation(angle.first, maxTranslation),
                translationY = -getTranslation(angle.second, maxTranslation),
            ), content = topLevelContent
        )
    }
}

private fun getRotationAngles(
    start: Pair<Float, Float>, end: Pair<Float, Float>, size: Size
): Pair<Float, Float> {
    val acceleration = 3
    val distances = getDistances(end, start)
    val rotationX = (distances.first / size.width) * maxAngle * acceleration
    val rotationY = (distances.second / size.height) * maxAngle * acceleration
    return Pair(rotationX, rotationY)
}

private fun getDistances(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Pair<Float, Float> {
    return Pair(
        p2.first - p1.first, p2.second - p1.second
    )
}

private fun getTranslation(angle: Float, maxDistance: Float): Float {
    return (angle / 90f) * maxDistance
}