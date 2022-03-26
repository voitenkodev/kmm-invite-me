package com.voitenko.dev.kmminviteme.android

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voitenko.dev.kmminviteme.android.superbox.FlipperBox
import com.voitenko.dev.kmminviteme.android.superbox.ParallaxBox
import com.voitenko.dev.kmminviteme.android.superbox.RotationType
import com.voitenko.dev.kmminviteme.android.superbox.ShimmerBox

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
    Text(
        modifier = modifier
            .size(120.dp)
            .padding(6.dp),
        text = "UKRAINE\nONE\nLOWE",
        color = Color.Black,
        fontSize = 22.sp,
        fontFamily = FontFamily.Monospace,
        textAlign = TextAlign.Justify,
    )
}

@Composable
fun BottomLevelContent(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Text(
            modifier = Modifier
                .size(120.dp)
                .padding(6.dp),
            text = "UKRAINE\nONE\nLOWE",
            color = Color.Gray,
            fontSize = 22.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Justify,
        )
    }
}