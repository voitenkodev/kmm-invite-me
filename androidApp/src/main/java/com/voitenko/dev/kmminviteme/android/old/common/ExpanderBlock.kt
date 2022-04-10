package com.voitenko.dev.kmminviteme.android.old.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.old.common.base.Description
import com.voitenko.dev.kmminviteme.android.old.common.base.Title
import com.voitenko.dev.kmminviteme.android.old.common.base.box.Expander
import com.voitenko.dev.kmminviteme.android.old.common.base.box.ExpanderContent
import com.voitenko.dev.kmminviteme.android.old.common.base.box.FlipType
import com.voitenko.dev.kmminviteme.android.old.common.base.box.FlipperContent
import com.voitenko.dev.kmminviteme.android.old.common.base.floatState
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme

const val defaultBlockSize = 56

@Composable
fun ExpanderBlock(
    modifier: Modifier = Modifier,
    heightExpand: Dp?,
    expand: Boolean,
    number: String,
    notes: String,
    content: @Composable (Float) -> Unit,
) {
    val alpha = floatState(value = expand, 0f to 1f)

    Row(modifier = modifier) {
        ExpanderItem(
            heightExpand = heightExpand,
            expand = expand,
            number = number
        ) { content.invoke(it) }
        ElementNotes(notes = notes, alpha = alpha.value)
    }
}

@Composable
private fun ExpanderItem(
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
        .clip(RoundedCornerShape(8.dp))
        .background(color = expander.color1, shape = RoundedCornerShape(8.dp)),
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