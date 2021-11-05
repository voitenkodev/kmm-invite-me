package com.voitenko.dev.kmminviteme.android.features.expander

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.base.Description
import com.voitenko.dev.kmminviteme.android.base.Title
import com.voitenko.dev.kmminviteme.android.base.box.Expander
import com.voitenko.dev.kmminviteme.android.base.box.ExpanderContent
import com.voitenko.dev.kmminviteme.android.base.box.FlipType
import com.voitenko.dev.kmminviteme.android.base.box.FlipperContent
import com.voitenko.dev.kmminviteme.android.base.floatState

private const val defaultBlockSize = 56

@Composable
fun ExpanderComponent(
    modifier: Modifier = Modifier,
    state: ExpanderFeature.State,
    content: @Composable (Float) -> Unit,
) {
    val alpha = floatState(value = state.isOpened, 0f to 1f)

    Row(modifier = modifier) {
        ExpanderElement(
            heightExpand = state.expandHeight?.dp,
            expand = state.isOpened,
            number = state.number
        ) { content.invoke(it) }
        ElementNotes(notes = state.notes, alpha = alpha.value)
    }
}

@Composable
private fun ExpanderElement(
    heightExpand: Dp?,
    expand: Boolean,
    number: String,
    content: @Composable (Float) -> Unit
) = ExpanderContent(
    color1 = AppTheme.colors.primary,
    color2 = AppTheme.colors.primary,
    height1 = heightExpand,
    height2 = defaultBlockSize.dp,
    width1 = null,
    width2 = defaultBlockSize.dp,
    state = expand,
    content = { expander -> ExpandBody(expander, number, content, expand) }
)

@Composable
private fun ExpandBody(
    expander: Expander,
    number: String,
    content: @Composable (Float) -> Unit,
    isExpand: Boolean
) = Row(
    modifier = Modifier
        .size(expander.width, expander.height)
        .clip(AppTheme.shapes.medium)
        .background(color = expander.color1, shape = AppTheme.shapes.medium),
    content = {
        FlipperNumber(flip = isExpand, number = number)
        content.invoke(expander.alpha1)
    }
)

@Composable
private fun FlipperNumber(
    flip: Boolean,
    number: String
) = FlipperContent(
    flip = flip,
    type = FlipType.Y,
    frontContent = {
        ElementNumber(
            number = number,
            color = AppTheme.colors.primaryText.copy(alpha = 0.4f)
        )
    },
    backContent = {
        ElementNumber(
            number = number,
            color = AppTheme.colors.primaryText
        )
    }
)

@Composable
private fun ElementNumber(
    number: String,
    color: Color
) = Title(
    modifier = Modifier.size(defaultBlockSize.dp),
    text = number,
    color = color
)

@Composable
private fun ElementNotes(
    notes: String,
    alpha: Float
) = Description(
    text = notes,
    color = AppTheme.colors.secondaryText,
    maxLines = 2,
    modifier = Modifier
        .height(defaultBlockSize.dp)
        .alpha(alpha)
        .padding(horizontal = 12.dp)
)