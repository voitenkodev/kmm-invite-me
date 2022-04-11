package com.voitenko.dev.kmminviteme.android.neomorph

import android.graphics.BlurMaskFilter
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class CornerShape

object Oval : CornerShape()

class RoundedCorner(val radius: Dp) : CornerShape()

abstract class NeuShape(open val cornerShape: CornerShape) {
    abstract fun draw(drawScope: ContentDrawScope, lightSource: LightSource)
}

class Flat(override val cornerShape: CornerShape) : NeuShape(cornerShape) {

    override fun draw(drawScope: ContentDrawScope, lightSource: LightSource) {
        drawScope.drawBlurredBackground(lightSource, this.cornerShape)
        drawScope.drawContent()
    }

    private fun ContentDrawScope.drawBlurredBackground(
        lightSource: LightSource, cornerShape: CornerShape
    ) = drawIntoCanvas { canvas ->
        val elevation = 4.dp.toPx()
        val blurRadius = elevation * .95f
        val displacement = elevation * .6f
        val dark = Color.Blue.toArgb()
        val light = Color.Green.toArgb()
        if (blurRadius <= 0) return@drawIntoCanvas

        val paintDark = Paint().also { p ->
            p.asFrameworkPaint().also { nativePaint ->
                nativePaint.isAntiAlias = true
                nativePaint.isDither = true
                nativePaint.color = dark
                nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
        }

        val paintLight = Paint().also { p ->
            p.asFrameworkPaint().also { nativePaint ->
                nativePaint.isAntiAlias = true
                nativePaint.isDither = true
                nativePaint.color = light
                nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
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
        when (cornerShape) {
            is Oval -> canvas.drawOval(0f, 0f, this.size.width, this.size.height, paintLight)
            is RoundedCorner -> canvas.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                cornerShape.radius.toPx(),
                cornerShape.radius.toPx(),
                paintLight
            )
        }
        canvas.restore()
        canvas.save()
        canvas.translate(backgroundDarkOffset.x, backgroundDarkOffset.y)
        when (cornerShape) {
            is Oval -> canvas.drawOval(0f, 0f, this.size.width, this.size.height, paintDark)
            is RoundedCorner -> canvas.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                cornerShape.radius.toPx(),
                cornerShape.radius.toPx(),
                paintDark
            )
        }
        canvas.restore()
    }
}

class Pressed(override val cornerShape: CornerShape) : NeuShape(cornerShape) {
    override fun draw(drawScope: ContentDrawScope, lightSource: LightSource) {
        drawScope.drawContent()
        drawScope.drawForeground(lightSource, this.cornerShape)
    }

    private fun ContentDrawScope.drawForeground(
        lightSource: LightSource, cornerShape: CornerShape
    ) {
        val elevation = 4.dp.toPx()
        val dark = Color.Blue.toArgb()
        val light = Color.Green.toArgb()

        drawIntoCanvas { canvas ->

            val blurRadius = elevation * 0.6f
            val strokeWidth = elevation * .95f

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
            when (cornerShape) {
                is Oval -> {
                    val visiblePath = Path().also { p ->
                        p.moveTo(0f, 0f)
                        p.addOval(Rect(0f, 0f, this.size.width, this.size.height))
                    }
                    canvas.clipPath(visiblePath)
                    canvas.translate(backgroundDarkOffset.x, backgroundDarkOffset.y)
                    canvas.drawOval(
                        -strokeWidth,
                        -strokeWidth,
                        this.size.width + strokeWidth,
                        this.size.height + strokeWidth,
                        paintDark
                    )
                }
                is RoundedCorner -> {
                    val cornerRadius = cornerShape.radius.toPx()
                    val visiblePath = Path().also { p ->
                        p.moveTo(0f, 0f)
                        p.addRoundRect(
                            RoundRect(
                                0f,
                                0f,
                                this.size.width,
                                this.size.height,
                                cornerRadius,
                                cornerRadius,
                            )
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
                }
            }
            canvas.restore()
            canvas.save()

            when (cornerShape) {
                is Oval -> {
                    val visiblePath = Path().also { p ->
                        p.moveTo(0f, 0f)
                        p.addOval(Rect(0f, 0f, this.size.width, this.size.height))
                    }
                    canvas.clipPath(visiblePath)
                    canvas.translate(backgroundLightOffset.x, backgroundLightOffset.y)
                    canvas.drawOval(
                        -strokeWidth,
                        -strokeWidth,
                        this.size.width + strokeWidth,
                        this.size.height + strokeWidth,
                        paintLight
                    )
                }
                is RoundedCorner -> {
                    val cornerRadius = cornerShape.radius.toPx()
                    val visiblePath = Path().also { p ->
                        p.moveTo(0f, 0f)
                        p.addRoundRect(
                            RoundRect(
                                0f,
                                0f,
                                this.size.width,
                                this.size.height,
                                cornerRadius,
                                cornerRadius,
                            )
                        )
                    }

                    canvas.clipPath(visiblePath)
                    canvas.translate(backgroundLightOffset.x, backgroundLightOffset.y)
                    canvas.drawRoundRect(
                        -strokeWidth,
                        -strokeWidth,
                        this.size.width + strokeWidth,
                        this.size.height + strokeWidth,
                        cornerRadius,
                        cornerRadius,
                        paintLight
                    )
                }
            }
            canvas.restore()
        }
    }
}

