package com.voitenko.dev.kmminviteme.android.old.common.base

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme
import com.voitenko.dev.kmminviteme.android.old.common.base.box.Center

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Black,
) = Center(modifier = modifier) {
    SimpleText(
        modifier = Modifier.padding(bottom = 6.dp),
        text = text,
        textStyle = AppTheme.typography.H3.copy(color = color),
        maxLines = maxLines,
    )
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = AppTheme.colors.primaryText,
) = Center(modifier = modifier) {
    SimpleText(
        modifier = Modifier,
        text = text,
        textStyle = AppTheme.typography.BODY2.copy(color = color),
        maxLines = maxLines,
    )
}

@Composable
fun ToolBar(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = AppTheme.colors.primaryText,
    isCollapsed: Boolean = true
) {
    val transition = updateTransition(isCollapsed, label = "")
    val height by transition.animateDp(
        transitionSpec = { tween(durationMillis = 500) }, label = ""
    ) { state -> if (state) 38.dp else 80.dp }
    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) }, label = ""
    ) { state -> if (state) 0.7f else 1f }

    Center(
        modifier = modifier
            .height(height),
    ) {
        SimpleText(
            modifier = Modifier
                .scale(scale)
                .padding(start = 20.dp, end = 20.dp),
            text = text,
            textStyle = AppTheme.typography.H1.copy(color = color),
            maxLines = 1,
        )
    }
}

@Composable
private fun SimpleText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    maxLines: Int = Int.MAX_VALUE
) = ProvideTextStyle(value = textStyle) {
    BasicText(
        modifier = modifier,
        text = text,
        overflow = TextOverflow.Clip,
        style = textStyle,
        maxLines = maxLines,
    )
}