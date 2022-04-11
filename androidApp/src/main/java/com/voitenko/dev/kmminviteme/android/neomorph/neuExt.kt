package com.voitenko.dev.kmminviteme.android.neomorph

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.dp

enum class LightSource {
    LEFT_TOP,
    RIGHT_TOP,
    LEFT_BOTTOM,
    RIGHT_BOTTOM;

    fun opposite(): LightSource {
        return when (this) {
            LEFT_TOP -> RIGHT_BOTTOM
            RIGHT_TOP -> LEFT_BOTTOM
            LEFT_BOTTOM -> RIGHT_TOP
            RIGHT_BOTTOM -> LEFT_TOP
        }
    }
}

fun Modifier.neu(
    shape: NeuShape = Flat(RoundedCorner(0.dp)),
    lightSource: LightSource = LightSource.LEFT_TOP
) = this.then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            shape.draw(this, lightSource)
        }
    }
)