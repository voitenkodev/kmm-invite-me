package com.voitenko.dev.kmminviteme.android.features.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.voitenko.dev.kmminviteme.android.theme.AppTheme

@Composable
fun Input(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    state: InputFeature.State,
    onValueChange: ((String) -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = InputField(
    modifier = modifier,
    text = state.text,
    onValueChange = onValueChange,
    onClick = onClick,
    placeholder = state.placeholder,
    textStyle = textStyle,
    maxLines = maxLines,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions
)

@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: ((String) -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    placeholder: String = "",
    textStyle: TextStyle = AppTheme.typography.input,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) = ProvideTextStyle(value = textStyle) {

    TextField(
        modifier = modifier.clickable { onClick?.invoke() },
        value = text,
        maxLines = maxLines,
        enabled = onClick == null && onValueChange != null,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = { onValueChange?.invoke(it) },
        placeholder = { PlaceHolder(style = textStyle, text = placeholder) },
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

@Composable
private fun PlaceHolder(style: TextStyle, text: String) = ProvideTextStyle(value = style) {
    Text(modifier = Modifier.alpha(0.6f), text = text, maxLines = 1)
}