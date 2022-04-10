package com.voitenko.dev.kmminviteme.android.neomorph

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neu(
    lightShadowColor: Color,
    darkShadowColor: Color,
    shadowElevation: Dp = 4.dp,
    shape: NeuShape = Flat(RoundedCorner(0.dp)),
    lightSource: LightSource = LightSource.LEFT_TOP
) = this.then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            val style = NeuStyle(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                shadowElevation = shadowElevation,
                lightSource = lightSource
            )
            shape.draw(this, style)
        }
    }
)