package com.voitenko.dev.kmminviteme.android.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme
import com.voitenko.dev.kmminviteme.android.designsystem.Theme

@Composable
fun H1Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.H1,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun H2Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.H2,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun H3Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.H2,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun BODY1Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.BODY1,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun BODY2Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.BODY2,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun BODY3Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.BODY3,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun BUTTON1Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.BUTTON1,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Composable
fun BUTTON2Text(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    label: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = Text(
    modifier = modifier,
    text = text,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.BUTTON1,
    maxLines = maxLines,
    label = label,
    placeholder = placeholder,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
)

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H1")
@Composable
private fun H1_Preview() {
    Theme {
        Column {
            H1Text(text = "Text Helper")
            H1Text(text = "", placeholder = "Placeholder Helper")
            H1Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H2")
@Composable
private fun H2_Preview() {
    Theme {
        Column {
            H2Text(text = "Text Helper")
            H2Text(text = "", placeholder = "Placeholder Helper")
            H2Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "H3")
@Composable
private fun H3_Preview() {
    Theme {
        Column {
            H3Text(text = "Text Helper")
            H3Text(text = "", placeholder = "Placeholder Helper")
            H3Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY1")
@Composable
private fun BODY1_Preview() {
    Theme {
        Column {
            BODY1Text(text = "Text Helper")
            BODY1Text(text = "", placeholder = "Placeholder Helper")
            BODY1Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY2")
@Composable
private fun BODY2_Preview() {
    Theme {
        Column {
            BODY2Text(text = "Text Helper")
            BODY2Text(text = "", placeholder = "Placeholder Helper")
            BODY2Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BODY3")
@Composable
private fun BODY3_Preview() {
    Theme {
        Column {
            BODY3Text(text = "Text Helper")
            BODY3Text(text = "", placeholder = "Placeholder Helper")
            BODY3Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BUTTON1")
@Composable
private fun BUTTON1_Preview() {
    Theme {
        Column {
            BUTTON1Text(text = "Text Helper")
            BUTTON1Text(text = "", placeholder = "Placeholder Helper")
            BUTTON1Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, name = "BUTTON2")
@Composable
private fun BUTTON2_Preview() {
    Theme {
        Column {
            BUTTON2Text(text = "Text Helper")
            BUTTON2Text(text = "", placeholder = "Placeholder Helper")
            BUTTON2Text(text = "Text Helper", label = "Label Helper")
        }
    }
}

@Composable
private fun Text(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = false,
    onValueChange: ((String) -> Unit)? = null,
    textStyle: TextStyle,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    ProvideTextStyle(value = textStyle) {
        TextField(
            modifier = modifier,
            value = text,
            onValueChange = { onValueChange?.invoke(it) },
            maxLines = maxLines,
            enabled = enabled,
            readOnly = onValueChange == null,
            label = label?.let { { Inner(style = textStyle, text = it) } },
            placeholder = placeholder?.let { { Inner(style = textStyle, text = it) } },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = textStyle.color,
                disabledTextColor = textStyle.color.copy(alpha = 0.6f),
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Composable
private fun Inner(style: TextStyle, text: String) = ProvideTextStyle(value = style) {
    androidx.compose.material.Text(modifier = Modifier.alpha(0.6f), text = text, maxLines = 1)
}