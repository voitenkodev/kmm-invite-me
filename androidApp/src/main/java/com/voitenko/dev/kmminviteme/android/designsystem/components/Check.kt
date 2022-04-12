package com.voitenko.dev.kmminviteme.android.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.designsystem.components.box.PressedBox

@Preview(name = "switch")
@Composable
fun CheckBox(
    initial: Boolean = false
) {
    val almostWhite = Color(236, 234, 235)
    val select = rememberSaveable { mutableStateOf(initial) }

    PressedBox(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(2.dp))
            .clickable { select.value = select.value.not() },
        radius = 2.dp,
        color = almostWhite
    ) {
        AnimatedVisibility(
            enter = fadeIn(),
            exit = fadeOut(),
            visible = select.value
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                modifier = Modifier.size(36.dp),
                contentDescription = "Clear"
            )
        }
    }
}