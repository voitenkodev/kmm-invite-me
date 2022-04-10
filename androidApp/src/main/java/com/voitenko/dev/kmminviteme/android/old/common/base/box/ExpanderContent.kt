package com.voitenko.dev.kmminviteme.android.old.common.base.box

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class Expander(
    val width: Dp,
    val height: Dp,
    val color1: Color,
    val color2: Color,
    val alpha1: Float,
    val alpha2: Float,
    val radius: Int?,
    val state: Boolean
)

@Composable
fun ExpanderContent(
    color1: Color = Color.White,
    color2: Color = Color.Red,
    height1: Dp? = null,
    height2: Dp? = null,
    width1: Dp? = null,
    width2: Dp? = null,
    radius1: Int? = null,
    radius2: Int? = null,
    state: Boolean,
    content: @Composable (Expander) -> Unit,
) = BoxWithConstraints(modifier = Modifier.wrapContentSize()) {

    val transition = updateTransition(state, label = "")

    val dur1 = 700
    val dur2 = 1100
    val dur3 = 400

    val _width by transition.animateDp(
        transitionSpec = { tween(durationMillis = dur1) }, label = ""
    ) { state -> if (state) width1 ?: maxWidth else width2 ?: maxHeight }

    val _height by transition.animateDp(
        transitionSpec = { tween(durationMillis = dur1) }, label = ""
    ) { state -> if (state) height1 ?: maxHeight else height2 ?: maxHeight }

    val _color1 by transition.animateColor(
        transitionSpec = { tween(durationMillis = dur2) }, label = ""
    ) { state -> if (state) color1 else color2 }

    val _color2 by transition.animateColor(
        transitionSpec = { tween(durationMillis = dur3) }, label = ""
    ) { state -> if (state) color2 else color1 }

    val _alpha1 by transition.animateFloat(
        transitionSpec = { tween(durationMillis = dur1) }, label = ""
    ) { state -> if (state) 1f else 0f }

    val _radius by transition.animateInt(
        transitionSpec = { tween(durationMillis = dur2, easing = FastOutLinearInEasing) },
        label = ""
    ) { state ->
        if (state) radius1 ?: 0 else radius2 ?: 0
    }

    val _alpha2 by transition.animateFloat(
        transitionSpec = { tween(durationMillis = dur1) }, label = ""
    ) { state -> if (state) 0f else 1f }

    val box = Expander(
        width = _width,
        height = _height,
        color1 = _color1,
        color2 = _color2,
        alpha1 = _alpha1,
        alpha2 = _alpha2,
        radius = _radius,
        state = state,
    )
    content.invoke(box)
}