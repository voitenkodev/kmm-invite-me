package com.voitenko.dev.kmminviteme.android.designsystem

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class LightSource {
    LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM;

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
    radius: Dp = 0.dp,
    lightSource: LightSource = LightSource.LEFT_TOP,
    pressed: Boolean = false,
) = this.then(object : DrawModifier {
    override fun ContentDrawScope.draw() {
        if (pressed) drawForeground(lightSource, radius)
        else drawBackground(lightSource, radius)
        drawContent()
    }

    fun ContentDrawScope.drawBackground(lightSource: LightSource, radius: Dp) =
        drawIntoCanvas { canvas ->
            val elevation = 4.dp.toPx()
            val blurRadius = elevation * .95f
            val displacement = elevation * .6f
            val dark = Color.Gray.toArgb()
            val light = Color.White.toArgb()

            val paintDark = Paint().also { p ->
                p.asFrameworkPaint().also {
                    it.isAntiAlias = true
                    it.isDither = true
                    it.color = dark
                    it.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                }
            }

            val paintLight = Paint().also { p ->
                p.asFrameworkPaint().also {
                    it.isAntiAlias = true
                    it.isDither = true
                    it.color = light
                    it.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                }
            }
            val backgroundDarkOffset = when (lightSource.opposite()) {
                LightSource.LEFT_TOP -> Offset(-displacement, -displacement)
                LightSource.RIGHT_TOP -> Offset(displacement, -displacement)
                LightSource.LEFT_BOTTOM -> Offset(-displacement, displacement)
                LightSource.RIGHT_BOTTOM -> Offset(displacement, displacement)
            }
            val backgroundLightOffset = when (lightSource) {
                LightSource.LEFT_TOP -> Offset(-displacement, -displacement)
                LightSource.RIGHT_TOP -> Offset(displacement, -displacement)
                LightSource.LEFT_BOTTOM -> Offset(-displacement, displacement)
                LightSource.RIGHT_BOTTOM -> Offset(displacement, displacement)
            }

            canvas.save()
            canvas.translate(backgroundLightOffset.x, backgroundLightOffset.y)
            canvas.drawRoundRect(
                0f, 0f, size.width, size.height, radius.toPx(), radius.toPx(), paintLight
            )
            canvas.restore()
            canvas.save()
            canvas.translate(backgroundDarkOffset.x, backgroundDarkOffset.y)
            canvas.drawRoundRect(
                0f, 0f, size.width, size.height, radius.toPx(), radius.toPx(), paintDark
            )
            canvas.restore()
        }

    private fun ContentDrawScope.drawForeground(lightSource: LightSource, radius: Dp) =
        drawIntoCanvas { canvas ->
            val elevation = 4.dp.toPx()
            val dark = Color.Gray.toArgb()
            val light = Color.White.toArgb()
            val blurRadius = elevation * 0.6f
            val strokeWidth = elevation * .95f
            val cornerRadius = radius.toPx()

            val paintDark = Paint().also { p ->
                p.asFrameworkPaint().also { nativePaint ->
                    nativePaint.isAntiAlias = true
                    nativePaint.color = dark
                    nativePaint.strokeWidth = strokeWidth
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                }
            }
            val paintLight = Paint().also { p ->
                p.asFrameworkPaint().also { nativePaint ->
                    nativePaint.isAntiAlias = true
                    nativePaint.color = light
                    nativePaint.strokeWidth = strokeWidth
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                }
            }
            val backgroundDarkOffset = when (lightSource) {
                LightSource.LEFT_TOP -> Offset(strokeWidth, strokeWidth)
                LightSource.RIGHT_TOP -> Offset(-strokeWidth, strokeWidth)
                LightSource.LEFT_BOTTOM -> Offset(strokeWidth, -strokeWidth)
                LightSource.RIGHT_BOTTOM -> Offset(-strokeWidth, -strokeWidth)
            }
            val backgroundLightOffset = when (lightSource.opposite()) {
                LightSource.LEFT_TOP -> Offset(strokeWidth, strokeWidth)
                LightSource.RIGHT_TOP -> Offset(-strokeWidth, strokeWidth)
                LightSource.LEFT_BOTTOM -> Offset(strokeWidth, -strokeWidth)
                LightSource.RIGHT_BOTTOM -> Offset(-strokeWidth, -strokeWidth)
            }

            canvas.save()

            val visiblePath = Path().also { p ->
                p.moveTo(0f, 0f)
                p.addRoundRect(
                    RoundRect(0f, 0f, size.width, size.height, cornerRadius, cornerRadius)
                )
            }

            canvas.clipPath(visiblePath)
            canvas.translate(backgroundDarkOffset.x, backgroundDarkOffset.y)
            canvas.drawRoundRect(
                -strokeWidth,
                -strokeWidth,
                this.size.width + strokeWidth,
                this.size.height + strokeWidth,
                cornerRadius,
                cornerRadius,
                paintDark
            )
            canvas.restore()

            canvas.save()

            val visiblePath2 = Path().also { p ->
                p.moveTo(0f, 0f)
                p.addRoundRect(
                    RoundRect(0f, 0f, size.width, size.height, cornerRadius, cornerRadius)
                )
            }

            canvas.clipPath(visiblePath2)
            canvas.translate(backgroundLightOffset.x, backgroundLightOffset.y)
            canvas.drawRoundRect(
                -strokeWidth,
                -strokeWidth,
                size.width + strokeWidth,
                size.height + strokeWidth,
                cornerRadius,
                cornerRadius,
                paintLight
            )
            canvas.restore()
        }
})