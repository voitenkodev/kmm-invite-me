package com.voitenko.dev.kmminviteme.android.designsystem.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme
import com.voitenko.dev.kmminviteme.android.designsystem.Theme

@Composable
fun H1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.H1,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun H2Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.H2,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun H3Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.H2,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun BODY1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.BODY1,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun BODY2Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.BODY2,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun BODY3Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.BODY3,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun BUTTON1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.BUTTON1,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Composable
fun BUTTON2Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(
    modifier = modifier,
    text = text,
    textStyle = AppTheme.typography.BUTTON1,
    maxLines = maxLines,
    color = color,
    textAlign = textAlign,
)

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H1")
@Composable
private fun H1_Preview() {
    Theme {
        H1Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H2")
@Composable
private fun H2_Preview() {
    Theme {
        H2Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H3")
@Composable
private fun H3_Preview() {
    Theme {
        H3Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY1")
@Composable
private fun BODY1_Preview() {
    Theme {
        BODY1Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY2")
@Composable
private fun BODY2_Preview() {
    Theme {
        BODY2Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY3")
@Composable
private fun BODY3_Preview() {
    Theme {
        BODY3Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BUTTON1")
@Composable
private fun BUTTON1_Preview() {
    Theme {
        BUTTON1Text(text = "Text Helper")
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BUTTON2")
@Composable
private fun BUTTON2_Preview() {
    Theme {
        BUTTON2Text(text = "Text Helper")
    }
}

@Composable
private fun Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    textAlign: TextAlign? = null,
    textStyle: TextStyle,
    maxLines: Int = Int.MAX_VALUE,
) {
    val innerColorTextStyle = if (color != null) {
        textStyle.copy(color = color)
    } else textStyle

    val innerTextAlignTextStyle = if (textAlign != null) {
        innerColorTextStyle.copy(textAlign = textAlign)
    } else innerColorTextStyle


    BasicText(modifier = modifier,
        text = text,
        style = innerTextAlignTextStyle,
        maxLines = maxLines,
        onTextLayout = { tl: TextLayoutResult -> })
}


@Composable
private fun Inner(style: TextStyle, text: String) = ProvideTextStyle(value = style) {
    androidx.compose.material.Text(modifier = Modifier.alpha(0.6f), text = text, maxLines = 1)
}