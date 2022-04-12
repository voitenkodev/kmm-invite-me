package com.voitenko.dev.kmminviteme.android.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.designsystem.components.box.FlatBox
import com.voitenko.dev.kmminviteme.android.designsystem.components.box.FlipBox
import com.voitenko.dev.kmminviteme.android.designsystem.neu

@Preview(name = "switch")
@Composable
fun Switch(
    initial: Boolean = false,
) {
    val almostWhite = Color(236, 234, 235)
    val select = rememberSaveable { mutableStateOf(initial) }
    val align = animateFloatAsState(targetValue = if (select.value) -1f else 1f)

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(36.dp)
            .background(color = Color.White, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .clickable { select.value = select.value.not() },
    ) {
        FlatBox(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(28.dp)
                .align(BiasAlignment(align.value, 0f)),
            radius = 14.dp,
            color = almostWhite
        ) {
            FlipBox(
                frontContent = {
                    Icon(
                        imageVector = Icons.Default.Done,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(4.dp),
                        contentDescription = "Clear"
                    )
                },
                backContent = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(4.dp),
                        contentDescription = "Clear"
                    )
                },
                flip = select.value
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .neu(18.dp, pressed = true),
        )
    }
}