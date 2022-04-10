package com.voitenko.dev.kmminviteme.android

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.box.FlipperBox
import com.voitenko.dev.kmminviteme.android.box.ParallaxBox
import com.voitenko.dev.kmminviteme.android.box.RotationType
import com.voitenko.dev.kmminviteme.android.box.ShimmerBox
import com.voitenko.dev.kmminviteme.android.designsystem.components.H3Text

@Composable
fun TestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        ParallaxBox(topLevelContent = { TopLevelContent() },
            bottomLevelContent = { BottomLevelContent() })

        Spacer(Modifier.size(30.dp))

        FlipperBox(rotationType = RotationType.Y, frontContent = { flip ->
            TopLevelContent(modifier = Modifier.clickable { flip.invoke() })
        }, backContent = { flip ->
            BottomLevelContent(modifier = Modifier.clickable { flip.invoke() })
        })

        Spacer(Modifier.size(30.dp))

        ShimmerBox(gradient = listOf(Color(0xFF0057b8), Color(0xFFFFd700))) {
            TopLevelContent(modifier = Modifier.background(it))
        }
    }
}

@Composable
fun TopLevelContent(modifier: Modifier = Modifier) {
    H3Text(
        modifier = modifier
            .size(120.dp)
            .padding(6.dp),
        text = "UKRAINE\nONE\nLOWE",
    )
}

@Composable
fun BottomLevelContent(modifier: Modifier = Modifier) {
    H3Text(
        modifier = modifier
            .size(120.dp)
            .padding(6.dp),
        text = "UKRAINE\nONE\nLOWE",
    )
}