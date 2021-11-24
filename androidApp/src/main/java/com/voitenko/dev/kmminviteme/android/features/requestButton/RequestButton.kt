package com.voitenko.dev.kmminviteme.android.features.requestButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.common.base.Title
import com.voitenko.dev.kmminviteme.android.common.base.box.Expander
import com.voitenko.dev.kmminviteme.android.common.base.box.ExpanderContent
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme

@Composable
fun RequestButton(
    state: RequestButtonFeature.State,
    onClick: () -> Unit
) = ExpanderButtonBlock(
    color1 = state.request.color,
    color2 = RequestButtonFeature.State.Request.PROGRESS.color,
    text = state.text,
    state = state.isOpened,
    onClick = onClick
)

@Composable
private fun ExpanderButtonBlock(
    color1: Color,
    color2: Color,
    text: String,
    state: Boolean,
    onClick: () -> Unit
) = ExpanderContent(
    color1 = color1,
    color2 = color2,
    height1 = 56.dp,
    height2 = 56.dp,
    width1 = null,
    width2 = 56.dp,
    radius1 = 0,
    radius2 = 50,
    state = state,
    content = { expander -> ExpandBody(expander = expander, text = text, onClick = onClick) }
)

@Composable
private fun ExpandBody(
    expander: Expander,
    text: String,
    onClick: () -> Unit
) = Button(
    modifier = Modifier
        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        .size(width = expander.width, height = expander.height),
    onClick = onClick,
    shape = RoundedCornerShape(expander.radius ?: 0),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = expander.color1
    ),
    content = {
        if (expander.state) Title(
            modifier = Modifier
                .alpha(expander.alpha1)
                .fillMaxWidth(),
            text = text,
            color = AppTheme.colors.primary
        ) else CircularProgressIndicator(
            modifier = Modifier
                .size(23.dp, 23.dp)
                .alpha(expander.alpha2)
        )
    }
)